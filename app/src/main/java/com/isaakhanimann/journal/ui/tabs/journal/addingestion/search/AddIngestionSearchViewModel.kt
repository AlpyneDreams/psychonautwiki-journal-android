/*
 * Copyright (c) 2022-2023. Isaak Hanimann.
 * This file is part of PsychonautWiki Journal.
 *
 * PsychonautWiki Journal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * PsychonautWiki Journal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PsychonautWiki Journal.  If not, see https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package com.isaakhanimann.journal.ui.tabs.journal.addingestion.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaakhanimann.journal.data.room.experiences.ExperienceRepository
import com.isaakhanimann.journal.data.room.experiences.entities.CustomSubstance
import com.isaakhanimann.journal.data.room.experiences.relations.IngestionWithCompanion
import com.isaakhanimann.journal.data.substances.repositories.SearchRepository
import com.isaakhanimann.journal.data.substances.repositories.SubstanceRepository
import com.isaakhanimann.journal.ui.tabs.journal.addingestion.search.suggestion.models.PreviousDose
import com.isaakhanimann.journal.ui.tabs.journal.addingestion.search.suggestion.models.RouteWithDoses
import com.isaakhanimann.journal.ui.tabs.journal.addingestion.search.suggestion.models.SubstanceSuggestion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class AddIngestionSearchViewModel @Inject constructor(
    experienceRepo: ExperienceRepository,
    val substanceRepo: SubstanceRepository,
    private val searchRepo: SearchRepository,
) : ViewModel() {

    private val _searchTextFlow = MutableStateFlow("")
    val searchTextFlow = _searchTextFlow.asStateFlow()

    fun updateSearchText(searchText: String) {
        viewModelScope.launch {
            _searchTextFlow.emit(searchText)
        }
    }

    val filteredSubstancesFlow = combine(
        searchTextFlow,
        experienceRepo.getSortedLastUsedSubstanceNamesFlow(limit = 200)
    ) { searchText, recents ->
        return@combine searchRepo.getMatchingSubstances(
            searchText = searchText,
            filterCategories = emptyList(),
            recentlyUsedSubstanceNamesSorted = recents
        ).map { it.toSubstanceModel() }
    }.stateIn(
        initialValue = emptyList(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val customSubstancesFlow = experienceRepo.getCustomSubstancesFlow()

    val filteredCustomSubstancesFlow =
        customSubstancesFlow.combine(searchTextFlow) { customSubstances, searchText ->
            customSubstances.filter { custom ->
                custom.name.contains(other = searchText, ignoreCase = true)
            }
        }.stateIn(
            initialValue = emptyList(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000)
        )

    val filteredSuggestions: StateFlow<List<SubstanceSuggestion>> = combine(
        experienceRepo.getSortedIngestionsWithSubstanceCompanionsFlow(limit = 150),
        customSubstancesFlow,
        filteredSubstancesFlow,
        searchTextFlow
    ) { ingestions, customSubstances, filteredSubstances, searchText ->
        val suggestions = getSubstanceSuggestions(ingestions, customSubstances)
        return@combine suggestions.filter { sug ->
            filteredSubstances.any { it.name == sug.substanceName } || sug.substanceName.contains(
                other = searchText,
                ignoreCase = true
            )
        }
    }.stateIn(
        initialValue = emptyList(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )


    private fun getSubstanceSuggestions(
        ingestions: List<IngestionWithCompanion>,
        customSubstances: List<CustomSubstance>
    ): List<SubstanceSuggestion> {
        val grouped = ingestions.groupBy { it.ingestion.substanceName }
        return grouped.mapNotNull { entry ->
            val substanceName = entry.key
            val ingestionsGroupedBySubstance = entry.value
            val color =
                ingestionsGroupedBySubstance.firstOrNull()?.substanceCompanion?.color ?: return@mapNotNull null
            val isPredefinedSubstance = substanceRepo.getSubstance(substanceName) != null
            val isCustomSubstance = customSubstances.any { it.name == substanceName }
            val groupedRoute = ingestionsGroupedBySubstance.groupBy { it.ingestion.administrationRoute }
            if (!isPredefinedSubstance && !isCustomSubstance) {
                return@mapNotNull null
            } else {
                return@mapNotNull SubstanceSuggestion(
                    color = color,
                    substanceName = substanceName,
                    isCustom = isCustomSubstance,
                    routesWithDoses = groupedRoute
                        .map { routeEntry ->
                            RouteWithDoses(
                                route = routeEntry.key,
                                doses = routeEntry.value.map { ingestionWithCompanion ->
                                    PreviousDose(
                                        dose = ingestionWithCompanion.ingestion.dose,
                                        unit = ingestionWithCompanion.ingestion.units,
                                        isEstimate = ingestionWithCompanion.ingestion.isDoseAnEstimate
                                    )
                                }.distinct().take(6)
                            )
                        },
                    lastUsed = ingestionsGroupedBySubstance.maxOfOrNull { it.ingestion.time } ?: Instant.now()
                )
            }
        }
    }
}