package org.example.project.presentation.charts

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.bar.DefaultVerticalBar
import io.github.koalaplot.core.bar.VerticalBarPlot
import io.github.koalaplot.core.util.*
import io.github.koalaplot.core.xygraph.FloatLinearAxisModel
import io.github.koalaplot.core.xygraph.TickPosition
import io.github.koalaplot.core.xygraph.XYGraph
import io.github.koalaplot.core.xygraph.rememberAxisStyle
import org.example.project.domain.models.StatsByDistrict

private val YAxisRange = 0f..25f
private val XAxisRange = 0.5f..8.5f

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun BarPlot(
    thumbnail: Boolean = false,
    tickPositionState: TickPositionState,
    title: String,
    stats: List<StatsByDistrict>
) {
    val barChartEntries = remember(thumbnail) { barChartEntries(stats) }
    val colors = generateHueColorPalette(stats.size)
    ChartLayout(
        modifier = Modifier.padding(8.dp),
        title = {
            Text(
                title,
                color = Color.Black,
                style = MaterialTheme.typography.subtitle1,
            )
        }
    ) {
        XYGraph(
            xAxisModel = FloatLinearAxisModel(
                XAxisRange,
                minimumMajorTickIncrement = 1f,
                minimumMajorTickSpacing = 10.dp,
                minViewExtent = 3f,
                minorTickCount = 0
            ),
            yAxisModel = FloatLinearAxisModel(
                YAxisRange,
                minimumMajorTickIncrement = 1f,
                minorTickCount = 0
            ),
            xAxisStyle = rememberAxisStyle(
                tickPosition = tickPositionState.horizontalAxis,
                color = Color.LightGray
            ),
            xAxisLabels = {
                if (!thumbnail) {
                    AxisLabel(
                        modifier = Modifier.padding(top = 2.dp),
                        label = it.toString(0)
                    )
                }
            },
            xAxisTitle = {
                if (!thumbnail) {
                    AxisTitle(title = "Район")
                }
            },
            yAxisStyle = rememberAxisStyle(tickPosition = tickPositionState.verticalAxis),
            yAxisLabels = {
                if (!thumbnail) {
                    AxisLabel(
                        modifier = Modifier.absolutePadding(right = 2.dp),
                        label = it.toString(1)
                    )
                }
            },
            yAxisTitle = {
                if (!thumbnail) {
                    AxisTitle(
                        modifier = Modifier
                            .rotateVertically(VerticalRotation.COUNTER_CLOCKWISE)
                            .padding(bottom = 8.dp),
                        title = "Оценка"
                    )
                }
            },
            verticalMajorGridLineStyle = null
        ) {
            VerticalBarPlot(
                barChartEntries,
                bar = { index ->
                    DefaultVerticalBar(
                        brush = SolidColor(colors[0]),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        if (!thumbnail) {
                            HoverSurface { Text(stats[index].district) }
                        }
                    }
                },
                barWidth = 0.8f
            )
        }
    }
}

data class TickPositionState(
    val verticalAxis: TickPosition,
    val horizontalAxis: TickPosition
)