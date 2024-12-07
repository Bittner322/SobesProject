package org.example.project.ui.charts

import io.github.koalaplot.core.bar.DefaultVerticalBarPlotEntry
import io.github.koalaplot.core.bar.DefaultVerticalBarPosition
import io.github.koalaplot.core.bar.VerticalBarPlotEntry
import org.example.project.domain.models.StatsByDistrict

fun barChartEntries(stats: List<StatsByDistrict>): List<VerticalBarPlotEntry<Float, Float>> {
    return buildList {
        stats.forEachIndexed { index, fl ->
            add(DefaultVerticalBarPlotEntry((index + 1).toFloat(), DefaultVerticalBarPosition(0f, fl.rating.toFloat())))
        }
    }
}