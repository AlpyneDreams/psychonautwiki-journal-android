package com.isaakhanimann.healthassistant.ui.ingestions.ingestion.edit.membership

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.isaakhanimann.healthassistant.data.room.experiences.entities.Experience


@Composable
fun EditIngestionMembershipScreen(
    navigateBack: () -> Unit,
    viewModel: EditIngestionMembershipViewModel = hiltViewModel()
) {
    EditIngestionMembershipScreen(
        onTap = {
            viewModel.onTap(it)
            navigateBack()
        },
        selectedExperienceId = viewModel.selectedExperienceId,
        experiences = viewModel.experiences.collectAsState().value
    )
}

@Preview
@Composable
fun EditIngestionMembershipScreenPreview(
    @PreviewParameter(ExperiencesPreviewProvider::class) experiences: List<Experience>,
) {
    EditIngestionMembershipScreen(
        onTap = {},
        selectedExperienceId = null,
        experiences = experiences
    )
}

@Composable
fun EditIngestionMembershipScreen(
    onTap: (Int) -> Unit,
    selectedExperienceId: Int?,
    experiences: List<Experience>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Choose Experience") },
            )
        }
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (experiences.isEmpty()) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "There are no experiences yet")
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                items(experiences.size) { i ->
                    val exp = experiences[i]
                    val isSelected = exp.id == selectedExperienceId
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = { onTap(exp.id) },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = null // null recommended for accessibility with screen readers
                        )
                        Text(
                            text = exp.title,
                            style = MaterialTheme.typography.body1.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    if (i < experiences.size) {
                        Divider()
                    }
                }
            }
        }
    }

}