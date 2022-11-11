/*
 * Copyright (c) 2022.
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 3.0. If a copy of the MPL was not distributed with this file, You can obtain one at https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package com.isaakhanimann.journal.ui.journal.experience.timeline

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isaakhanimann.journal.data.room.experiences.entities.AdaptiveColor
import com.isaakhanimann.journal.data.substances.classes.roa.DurationRange
import com.isaakhanimann.journal.data.substances.classes.roa.DurationUnits
import com.isaakhanimann.journal.data.substances.classes.roa.RoaDuration
import com.isaakhanimann.journal.ui.journal.experience.DataForOneEffectLine
import java.time.Instant
import java.time.temporal.ChronoUnit

class TimelinesPreviewProvider :
    PreviewParameterProvider<List<DataForOneEffectLine>> {
    override val values: Sequence<List<DataForOneEffectLine>> = sequenceOf(
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = null,
                    peak = null,
                    offset = null,
                    total = null,
                    afterglow = null
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(4, ChronoUnit.HOURS)
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = null,
                    peak = null,
                    offset = null,
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = null
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(4, ChronoUnit.HOURS)
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = null,
                    offset = null,
                    total = null,
                    afterglow = null
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(4, ChronoUnit.HOURS)
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = null,
                    offset = null,
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = null
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(4, ChronoUnit.HOURS)
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 1.5f,
                        max = 2f,
                        units = DurationUnits.HOURS
                    ),
                    offset = null,
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = null
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(4, ChronoUnit.HOURS)
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 1.5f,
                        max = 2f,
                        units = DurationUnits.HOURS
                    ),
                    offset = null,
                    total = null,
                    afterglow = null
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(4, ChronoUnit.HOURS)
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 1.5f,
                        max = 2.5f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 4f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(4, ChronoUnit.HOURS)
            ),
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 1.5f,
                        max = 2.5f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 4f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 0.5f,
                horizontalWeight = 0.25f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(3, ChronoUnit.HOURS)
            ),
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 4f,
                        max = 6f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 3f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 6f,
                        max = 11f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.RED,
                startTime = Instant.now().minus(1, ChronoUnit.HOURS)
            ),
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = null,
                    comeup = null,
                    peak = null,
                    offset = null,
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = null
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.ORANGE,
                startTime = Instant.now()
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 4f,
                        max = 6f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 4f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(3, ChronoUnit.HOURS)
            ),
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 4f,
                        max = 6f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 3f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 6f,
                        max = 11f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.ORANGE,
                startTime = Instant.now().minus(1, ChronoUnit.HOURS)
            ),
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 4f,
                        max = 6f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 3f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 6f,
                        max = 11f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.GREEN,
                startTime = Instant.now()
            )
        ),
        listOf(
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 1.5f,
                        max = 2.5f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 4f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.BLUE,
                startTime = Instant.now().minus(20, ChronoUnit.MINUTES)
            ),
            DataForOneEffectLine(
                roaDuration = RoaDuration(
                    onset = DurationRange(
                        min = 20f,
                        max = 40f,
                        units = DurationUnits.MINUTES
                    ),
                    comeup = DurationRange(
                        min = 15f,
                        max = 30f,
                        units = DurationUnits.MINUTES
                    ),
                    peak = DurationRange(
                        min = 1.5f,
                        max = 2.5f,
                        units = DurationUnits.HOURS
                    ),
                    offset = DurationRange(
                        min = 2f,
                        max = 4f,
                        units = DurationUnits.HOURS
                    ),
                    total = DurationRange(
                        min = 3f,
                        max = 5f,
                        units = DurationUnits.HOURS
                    ),
                    afterglow = DurationRange(
                        min = 12f,
                        max = 48f,
                        units = DurationUnits.HOURS
                    )
                ),
                height = 1f,
                horizontalWeight = 0.5f,
                color = AdaptiveColor.GREEN,
                startTime = Instant.now()
            )
        )
    )
}