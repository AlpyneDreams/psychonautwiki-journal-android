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

package com.isaakhanimann.journal.ui.tabs.journal.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isaakhanimann.journal.data.room.experiences.entities.*
import com.isaakhanimann.journal.data.room.experiences.relations.ExperienceWithIngestionsCompanionsAndRatings
import com.isaakhanimann.journal.data.room.experiences.relations.IngestionWithCompanion
import com.isaakhanimann.journal.data.substances.AdministrationRoute
import java.time.Instant
import java.time.temporal.ChronoUnit

class ExperienceWithIngestionsCompanionsAndRatingsPreviewProvider :
    PreviewParameterProvider<ExperienceWithIngestionsCompanionsAndRatings> {
    override val values: Sequence<ExperienceWithIngestionsCompanionsAndRatings> = sequenceOf(
        ExperienceWithIngestionsCompanionsAndRatings(
            experience = Experience(
                id = 0,
                title = "Day at Lake Geneva",
                text = "Some notes",
                isFavorite = true,
                sortDate = Instant.now().minus(2, ChronoUnit.HOURS),
                location = Location(name = "Max place", longitude = 4.0, latitude = 5.0)
            ),
            ingestionsWithCompanions = listOf(
                IngestionWithCompanion(
                    ingestion = Ingestion(
                        substanceName = "MDMA",
                        time = Instant.now().minus(2, ChronoUnit.HOURS),
                        administrationRoute = AdministrationRoute.ORAL,
                        dose = 90.0,
                        isDoseAnEstimate = false,
                        units = "mg",
                        experienceId = 0,
                        notes = null,
                        stomachFullness = StomachFullness.EMPTY
                    ),
                    substanceCompanion = SubstanceCompanion(
                        substanceName = "MDMA",
                        color = AdaptiveColor.PINK
                    )
                ),
                IngestionWithCompanion(
                    ingestion = Ingestion(
                        substanceName = "Cocaine",
                        time = Instant.now().minus(1, ChronoUnit.HOURS),
                        administrationRoute = AdministrationRoute.INSUFFLATED,
                        dose = 30.0,
                        isDoseAnEstimate = false,
                        units = "mg",
                        experienceId = 0,
                        notes = null,
                        stomachFullness = null
                    ),
                    substanceCompanion = SubstanceCompanion(
                        substanceName = "Cocaine",
                        color = AdaptiveColor.BLUE
                    )
                ),
                IngestionWithCompanion(
                    ingestion = Ingestion(
                        substanceName = "Cocaine",
                        time = Instant.now().minus(30, ChronoUnit.MINUTES),
                        administrationRoute = AdministrationRoute.INSUFFLATED,
                        dose = 20.0,
                        isDoseAnEstimate = false,
                        units = "mg",
                        experienceId = 0,
                        notes = null,
                        stomachFullness = null
                    ),
                    substanceCompanion = SubstanceCompanion(
                        substanceName = "Cocaine",
                        color = AdaptiveColor.BLUE
                    )
                )
            ),
            ratings = listOf(
                ShulginRating(
                    time = Instant.now().minus(30, ChronoUnit.MINUTES),
                    creationDate = Instant.now(),
                    option = ShulginRatingOption.TWO_PLUS,
                    experienceId = 0
                ),
                ShulginRating(
                    time = Instant.now().minus(15, ChronoUnit.MINUTES),
                    creationDate = Instant.now(),
                    option = ShulginRatingOption.THREE_PLUS,
                    experienceId = 0
                ),
                ShulginRating(
                    time = Instant.now().minus(5, ChronoUnit.MINUTES),
                    creationDate = Instant.now(),
                    option = ShulginRatingOption.TWO_PLUS,
                    experienceId = 0
                )
            )
        ),
        ExperienceWithIngestionsCompanionsAndRatings(
            experience = Experience(
                id = 0,
                title = "This one has a very very very long title in case somebody wants to be creative with the naming.",
                text = "Some notes",
                isFavorite = true,
                sortDate = Instant.now(),
                location = null
            ),
            ingestionsWithCompanions = listOf(
                IngestionWithCompanion(
                    ingestion = Ingestion(
                        substanceName = "MDMA",
                        time = Instant.now(),
                        administrationRoute = AdministrationRoute.ORAL,
                        dose = 90.0,
                        isDoseAnEstimate = false,
                        units = "mg",
                        experienceId = 0,
                        notes = null,
                        stomachFullness = null
                    ),
                    substanceCompanion = SubstanceCompanion(
                        substanceName = "MDMA",
                        color = AdaptiveColor.PINK
                    )
                ),
                IngestionWithCompanion(
                    ingestion = Ingestion(
                        substanceName = "Cocaine",
                        time = Instant.now(),
                        administrationRoute = AdministrationRoute.INSUFFLATED,
                        dose = 20.0,
                        isDoseAnEstimate = false,
                        units = "mg",
                        experienceId = 0,
                        notes = null,
                        stomachFullness = null
                    ),
                    substanceCompanion = SubstanceCompanion(
                        substanceName = "Cocaine",
                        color = AdaptiveColor.BLUE
                    )
                )
            ),
            ratings = listOf(
                ShulginRating(
                    time = Instant.now().minus(15, ChronoUnit.MINUTES),
                    creationDate = Instant.now(),
                    option = ShulginRatingOption.FOUR_PLUS,
                    experienceId = 0
                )
            )
        )
    )
}