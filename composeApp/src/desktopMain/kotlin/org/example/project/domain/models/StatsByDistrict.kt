package org.example.project.domain.models

import org.example.sqldelight.homes.data.SelectRatingStatsByDistricts

data class StatsByDistrict(
    val district: String,
    val rating: Double?
)

fun SelectRatingStatsByDistricts.toStatsByDistrict(): StatsByDistrict {
    return StatsByDistrict(
        district = district,
        rating = AVG
    )
}