package org.example.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.data.repository.DatabaseRepository
import org.example.project.ui.charts.PieChartMain
import org.jetbrains.compose.resources.painterResource
import sobesproject.composeapp.generated.resources.Res
import sobesproject.composeapp.generated.resources.settings

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
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
            var containerWidth = LocalWindowInfo.current.containerSize.width
            var tabRowsWidth by remember { mutableStateOf(0) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged {
                        containerWidth = it.width
                    }
                    .padding(top = it.calculateTopPadding())
            ) {
                ScrollableTabRow(
                    backgroundColor = Color.Transparent,
                    selectedTabIndex = selectedTabIndex.value,
                    modifier = Modifier.fillMaxWidth(),
                    edgePadding = ((containerWidth.dp - tabRowsWidth.dp) / 2).coerceAtLeast(0.dp)
                ) {
                    val tabWidths = remember { mutableStateListOf<Int>() }
                    Tabs.entries.forEachIndexed { index, currentTab ->
                        Tab(
                            modifier = Modifier
                                .onSizeChanged { size ->
                                    if (index < tabWidths.size) {
                                        tabWidths[index] = size.width
                                    } else {
                                        tabWidths.add(size.width)
                                    }
                                    tabRowsWidth = tabWidths.sum()
                                },
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
                                PieChartMain()
                            }
                            1 -> {

                            }
                            2 -> {

                            }
                        }
                    }
                }
            }
        }
}