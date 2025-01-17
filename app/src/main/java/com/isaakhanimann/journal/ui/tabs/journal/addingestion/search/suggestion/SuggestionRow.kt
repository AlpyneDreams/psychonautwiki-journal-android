/*
 * Copyright (c) 2023. Isaak Hanimann.
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

package com.isaakhanimann.journal.ui.tabs.journal.addingestion.search.suggestion

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.isaakhanimann.journal.data.substances.AdministrationRoute
import com.isaakhanimann.journal.ui.tabs.journal.addingestion.search.ColorCircle
import com.isaakhanimann.journal.ui.tabs.journal.addingestion.search.suggestion.models.SubstanceSuggestion
import com.isaakhanimann.journal.ui.tabs.journal.components.RelativeDateTextNew
import com.isaakhanimann.journal.ui.tabs.search.substance.roa.toReadableString
import com.isaakhanimann.journal.ui.theme.horizontalPadding

@Preview(showBackground = true)
@Composable
fun SuggestionRowPreview(@PreviewParameter(SubstanceSuggestionProvider::class) substanceSuggestion: SubstanceSuggestion) {
    SuggestionRow(
        substanceRow = substanceSuggestion,
        navigateToDose = { _: String, _: AdministrationRoute -> },
        navigateToCustomDose = { _: String, _: AdministrationRoute -> },
        navigateToChooseTime = { _: String, _: AdministrationRoute, _: Double?, _: String?, _: Boolean -> }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionRow(
    substanceRow: SubstanceSuggestion,
    navigateToDose: (substanceName: String, route: AdministrationRoute) -> Unit,
    navigateToCustomDose: (substanceName: String, route: AdministrationRoute) -> Unit,
    navigateToChooseTime: (substanceName: String, route: AdministrationRoute, dose: Double?, units: String?, isEstimate: Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 5.dp)
            .padding(horizontal = horizontalPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ColorCircle(adaptiveColor = substanceRow.color)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = substanceRow.substanceName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            RelativeDateTextNew(
                dateTime = substanceRow.lastUsed,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        substanceRow.routesWithDoses.forEach { routeWithDoses ->
            Column {
                Text(
                    text = routeWithDoses.route.displayText,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 3.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                FlowRow(mainAxisSpacing = 5.dp) {
                    routeWithDoses.doses.forEach { previousDose ->
                        SuggestionChip(
                            onClick = {
                                if (substanceRow.isCustom) {
                                    navigateToChooseTime(
                                        substanceRow.substanceName,
                                        routeWithDoses.route,
                                        previousDose.dose,
                                        previousDose.unit,
                                        previousDose.isEstimate
                                    )
                                } else {
                                    navigateToChooseTime(
                                        substanceRow.substanceName,
                                        routeWithDoses.route,
                                        previousDose.dose,
                                        previousDose.unit,
                                        previousDose.isEstimate
                                    )
                                }
                            },
                            label = {
                                if (previousDose.dose != null) {
                                    val estimate =
                                        if (previousDose.isEstimate) "~" else ""
                                    Text(text = "$estimate${previousDose.dose.toReadableString()} ${previousDose.unit ?: ""}")
                                } else {
                                    Text(text = "Unknown")
                                }
                            },
                        )
                    }
                    SuggestionChip(onClick = {
                        if (substanceRow.isCustom) {
                            navigateToCustomDose(
                                substanceRow.substanceName, routeWithDoses.route
                            )
                        } else {
                            navigateToDose(
                                substanceRow.substanceName, routeWithDoses.route
                            )
                        }
                    }, label = { Text("Other") })
                }
            }
        }
    }
}