package org.example.project.domain.models

import org.example.sqldelight.homes.data.Homes

data class Home(
    val id: Long,
    val address: String,
    val district: String,
    val rating: Long?
)

fun Homes.toDomain(): Home {
    return Home(
        id = id,
        address = address,
        district = district,
        rating = rating
    )
}