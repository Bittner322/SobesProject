package org.example.project.domain.models

import org.example.sqldelight.homes.data.SelectRatingStatsByOverall

data class OverallStat(
    val rating: Double
) {
    companion object {
        val empty = OverallStat(
            rating = 0.0
        )
    }
}

fun SelectRatingStatsByOverall.toDomain(): OverallStat {
    return OverallStat(
        rating = AVG ?: 0.0
    )
}