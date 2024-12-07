package org.example.project.ui.charts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.pie.*
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.util.generateHueColorPalette

internal val padding = 8.dp
internal val fibonacci = mutableStateListOf(1.0f, 1.0f, 2.0f, 3.0f, 5.0f, 8.0f, 13.0f, 21.0f)
internal val fibonacciSum = fibonacci.sumOf { it.toDouble() }.toFloat()

private val colors = generateHueColorPalette(fibonacci.size)

@OptIn(ExperimentalKoalaPlotApi::class)
@Suppress("MagicNumber")
@Composable
fun PieChartMain(
    modifier: Modifier = Modifier,
) {
    ChartLayout(
        modifier = modifier.padding(padding),
        title = {
            Column {
                Text(
                    "Fibonacci Sequence",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    ) {
        PieChart(
            values = fibonacci,
            labelPositionProvider = CircularLabelPositionProvider(
                labelSpacing = 1.1f,
                labelPlacement = PieLabelPlacement.External,
            ),
            modifier = modifier.padding(start = padding).border(1.dp, Color.Black).padding(padding),
            slice = { i: Int ->
                DefaultSlice(
                    color = colors[i],
                    border = BorderStroke(
                        6.dp,
                        lerp(colors[i], Color.White, 0.2f)
                    ),
                    hoverExpandFactor = 1.05f,
                    hoverElement = { HoverSurface { Text(fibonacci[i].toString()) } }
                )
            },
            label = { i ->
                Text((fibonacci[i] / fibonacciSum).toString())
            },
            labelConnector = { i ->
                StraightLineConnector(
                    connectorColor = colors[i],
                    connectorStroke = strokes[0]
                )

            },
            maxPieDiameter = Dp.Infinity
        )
    }
}

private val strokes = buildList {
    add(Stroke(width = 1f))
    add(Stroke(width = 2f))
    add(Stroke(width = 3f))
    add(Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))))
    add(Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))))
    add(Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f))))
    add(Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f))))
}

private data class LabelOptionsState(
    val strokeStyle: Stroke = strokes[0],
    val straightLine: Boolean = false,
    val showLabels: Boolean = false,
    val labelSpacing: Float = 1.1f,
    val labelPlacement: PieLabelPlacement = PieLabelPlacement.External
)