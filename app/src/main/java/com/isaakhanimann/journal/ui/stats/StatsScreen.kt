package com.isaakhanimann.journal.ui.stats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.isaakhanimann.journal.ui.search.substance.roa.toReadableString
import com.isaakhanimann.journal.ui.theme.JournalTheme
import com.isaakhanimann.journal.ui.theme.horizontalPadding


@Composable
fun StatsScreen(
    viewModel: StatsViewModel = hiltViewModel(),
    navigateToSubstanceCompanion: (substanceName: String) -> Unit
) {
    StatsScreen(
        navigateToSubstanceCompanion = navigateToSubstanceCompanion,
        onTapOption = viewModel::onTapOption,
        statsModel = viewModel.statsModelFlow.collectAsState().value
    )
}

@Preview
@Composable
fun StatsPreview(
    @PreviewParameter(
        StatsPreviewProvider::class,
    ) statsModel: StatsModel
) {
    JournalTheme {
        StatsScreen(
            navigateToSubstanceCompanion = {},
            onTapOption = {},
            statsModel = statsModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    navigateToSubstanceCompanion: (substanceName: String) -> Unit,
    onTapOption: (option: TimePickerOption) -> Unit,
    statsModel: StatsModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (statsModel.areThereAnyIngestions) "Statistics Since ${statsModel.startDateText}" else "Statistics") }
            )
        }
    ) { padding ->
        if (!statsModel.areThereAnyIngestions) {
            EmptyScreenDisclaimer(
                title = "Nothing to Show Yet",
                description = "Start logging your ingestions to see an overview of your consumption pattern."
            )
        } else {
            Column(modifier = Modifier.padding(padding)) {
                TabRow(
                    selectedTabIndex = statsModel.selectedOption.tabIndex,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    TimePickerOption.values().forEachIndexed { index, option ->
                        Tab(
                            text = { Text(option.displayText) },
                            selected = statsModel.selectedOption.tabIndex == index,
                            onClick = { onTapOption(option) }
                        )
                    }
                }
                if (statsModel.statItems.isEmpty()) {
                    EmptyScreenDisclaimer(
                        title = "No Ingestions Since ${statsModel.selectedOption.longDisplayText}",
                        description = "Use a longer duration range to see statistics."
                    )
                } else {
                    val isDarkTheme = isSystemInDarkTheme()
                    Card(modifier = Modifier.padding(horizontal = horizontalPadding, vertical = 10.dp)) {
                        Column {
                            Text(
                                text = "Experiences",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                            )
                            Text(
                                text = "Substance counted once per experience",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                            )
                            BarChart(
                                buckets = statsModel.chartBuckets,
                                startDateText = statsModel.startDateText
                            )
                            Divider()
                            LazyColumn {
                                items(statsModel.statItems.size) { i ->
                                    val subStat = statsModel.statItems[i]
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                navigateToSubstanceCompanion(subStat.substanceName)
                                            }
                                            .padding(horizontal = horizontalPadding, vertical = 5.dp)
                                    ) {
                                        Surface(
                                            shape = CircleShape,
                                            color = subStat.color.getComposeColor(isDarkTheme),
                                            modifier = Modifier.size(25.dp)
                                        ) {}
                                        Column {
                                            Text(
                                                text = subStat.substanceName,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                            val addOn =
                                                if (subStat.experienceCount == 1) " experience" else " experiences"
                                            Text(
                                                text = subStat.experienceCount.toString() + addOn,
                                            )
                                        }
                                        Spacer(modifier = Modifier.weight(1f))
                                        Column(horizontalAlignment = Alignment.End) {
                                            val cumulativeDose = subStat.totalDose
                                            if (cumulativeDose != null) {
                                                Text(text = "total ${if (cumulativeDose.isEstimate) "~" else ""}${cumulativeDose.dose.toReadableString()} ${cumulativeDose.units}")
                                            } else {
                                                Text(text = "total dose unknown")
                                            }
                                            subStat.routeCounts.forEach {
                                                Text(
                                                    text = "${it.administrationRoute.displayText.lowercase()} ${it.count}x ",
                                                )
                                            }
                                        }

                                    }
                                    if (i < statsModel.statItems.size-1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyScreenDisclaimer(title: String, description: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = description,
                textAlign = TextAlign.Center
            )
        }
    }
}