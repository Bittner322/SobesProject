package org.example.project.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.project.domain.models.*
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

    suspend fun getCountOfData(): Long {
        return withContext(Dispatchers.IO) {
            homesQueries.selectCountOfData().executeAsOne()
        }
    }

    suspend fun getHomesWithoutRating(): Long {
        return withContext(Dispatchers.IO) {
            homesQueries.selectCountOfHomesWithoutRating().executeAsOne()
        }
    }

    suspend fun getCountOfDistricts(): List<String> {
        return withContext(Dispatchers.IO) {
            homesQueries.selectDistricts().executeAsList()
        }
    }
}