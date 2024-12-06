package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.bar.BulletGraphs
import io.github.koalaplot.core.style.KoalaPlotTheme
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.FloatLinearAxisModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sobesproject.composeapp.generated.resources.Res
import sobesproject.composeapp.generated.resources.settings

enum class Tabs(val text: String) {
    ByDistricts("По районам"),
    Overall("Общая"),
    OtherInfo("Иная информация"),
    EditTable("Изменить базу данных")
}

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
@Preview
fun App() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { Tabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    MaterialTheme {
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
                ScrollableTabRow(
                    backgroundColor = Color.Transparent,
                    selectedTabIndex = selectedTabIndex.value,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Tabs.entries.forEachIndexed { index, currentTab ->
                        Tab(
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
                        BulletGraphs {
                            bullet(FloatLinearAxisModel(0f..300f)) {
                                label {
                                    Column(
                                        horizontalAlignment = Alignment.End,
                                        modifier = Modifier.padding(end = KoalaPlotTheme.sizes.gap)
                                    ) {
                                        Text("Revenue 2005 YTD", textAlign = TextAlign.End)
                                        Text(
                                            "(US $ in thousands)",
                                            textAlign = TextAlign.End,
                                            style = MaterialTheme.typography.subtitle1
                                        )
                                    }
                                }
                                axis { labels { Text("${it.toInt()}") } }
                                comparativeMeasure(260f)
                                featuredMeasureBar(275f)
                                ranges(0f, 200f, 250f, 300f)
                            }
                        }
                    }
                }
            }
        }
    }
}