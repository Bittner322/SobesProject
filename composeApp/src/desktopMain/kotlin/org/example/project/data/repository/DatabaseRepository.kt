package org.example.project.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.project.domain.models.OverallStat
import org.example.project.domain.models.StatsByDistrict
import org.example.project.domain.models.toDomain
import org.example.project.domain.models.toStatsByDistrict
import org.example.sqldelight.homes.data.HomesQueries

class DatabaseRepository(
    private val homesQueries: HomesQueries
) {
    suspend fun getOverallRating(): OverallStat {
        return withContext(Dispatchers.IO) {
            homesQueries.selectRatingStatsByOverall().executeAsOne().toDomain()
        }
    }

    suspend fun getAllRatingByDistricts(): List<StatsByDistrict> {
        return withContext(Dispatchers.IO) {
            homesQueries.selectRatingStatsByDistricts().executeAsList()
                .map { it.toStatsByDistrict() }
        }
    }
}