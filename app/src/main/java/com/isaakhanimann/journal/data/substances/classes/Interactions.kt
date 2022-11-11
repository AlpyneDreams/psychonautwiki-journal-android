/*
 * Copyright (c) 2022.
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 3.0. If a copy of the MPL was not distributed with this file, You can obtain one at https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package com.isaakhanimann.journal.data.substances.classes

import androidx.compose.ui.graphics.Color

enum class InteractionType {
    DANGEROUS {
        override val color = Color(0xffFF7B66)
        override val dangerCount = 3
    },
    UNSAFE {
        override val color = Color(0xFFFFC466)
        override val dangerCount = 2
    },
    UNCERTAIN {
        override val color = Color(0xffFFF966)
        override val dangerCount = 1
    };

    abstract val color: Color
    abstract val dangerCount: Int
}

data class Interactions(
    val dangerous: List<String>,
    val unsafe: List<String>,
    val uncertain: List<String>
) {
    fun getInteractions(interactionType: InteractionType): List<String> {
        return when (interactionType) {
            InteractionType.DANGEROUS -> dangerous
            InteractionType.UNSAFE -> unsafe
            InteractionType.UNCERTAIN -> uncertain
        }
    }
}