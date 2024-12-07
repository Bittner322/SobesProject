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
import io.github.koalaplot.core.xygraph.TickPosition
import kotlinx.coroutines.launch
import org.example.project.presentation.charts.BarPlot
import org.example.project.presentation.charts.TickPositionState
import org.example.project.presentation.screens.Tabs
import org.jetbrains.compose.resources.painterResource
import sobesproject.composeapp.generated.resources.Res
import sobesproject.composeapp.generated.resources.settings

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    val statsByDistrict by viewModel.statsByDistrictFlow.collectAsState()

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
                elevation = 0.dp,
                actions = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(28.dp),
                        painter = painterResource(Res.drawable.settings),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
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
                            Column {

                            }
                        }

                        2 -> {

                        }
                    }
                }
            }
        }
    }
}