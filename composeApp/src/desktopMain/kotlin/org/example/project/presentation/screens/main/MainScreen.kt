package org.example.project.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.koalaplot.core.xygraph.TickPosition
import kotlinx.coroutines.launch
import org.example.project.presentation.charts.BarPlot
import org.example.project.presentation.charts.TickPositionState
import org.example.project.presentation.screens.Tabs

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    val statsByDistrict by viewModel.statsByDistrictFlow.collectAsState()
    val statsByOverall by viewModel.statsByOverallFlow.collectAsState()
    val countOfData by viewModel.countOfDataFlow.collectAsState()
    val countOfHomesWithoutRating by viewModel.homesWithoutRatingCountFlow.collectAsState()
    val countOfDistricts by viewModel.countOfDistrictsFlow.collectAsState()

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { Tabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Статистика удовлетворенности \uD83D\uDCAB")
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            TabRow(
                backgroundColor = Color.Transparent,
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tabs.entries.forEachIndexed { index, currentTab ->
                    Tab(
                        modifier = Modifier,
                        selected = selectedTabIndex.value == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = { Text(text = currentTab.text) }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (pagerState.currentPage) {
                        0 -> {
                            BarPlot(
                                title = "Статистика по районам",
                                tickPositionState = TickPositionState(
                                    verticalAxis = TickPosition.Inside,
                                    horizontalAxis = TickPosition.Inside
                                ),
                                stats = statsByDistrict
                            )
                        }

                        1 -> {
                            Column(
                                modifier = Modifier.align(Alignment.Center)
                            ) {
                                Text(
                                    text = "Средняя общая оценка:",
                                    fontSize = 32.sp
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    text = String.format("%.2f", statsByOverall.rating),
                                    fontSize = 54.sp
                                )
                            }
                        }

                        2 -> {
                            Column(
                                modifier = Modifier.align(Alignment.Center)
                            ) {
                                Text(
                                    text = "Иная информация:",
                                    fontSize = 32.sp
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    text = "Всего записей: $countOfData",
                                    fontSize = 24.sp
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    text = "Всего районов: $countOfDistricts",
                                    fontSize = 24.sp
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    text = "Домов без оценки: $countOfHomesWithoutRating",
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}