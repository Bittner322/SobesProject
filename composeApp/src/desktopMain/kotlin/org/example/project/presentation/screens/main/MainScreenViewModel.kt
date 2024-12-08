package org.example.project.presentation.screens.main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.DatabaseRepository
import org.example.project.domain.models.OverallStat
import org.example.project.domain.models.StatsByDistrict

class MainScreenViewModel(private val repository: DatabaseRepository) {
    private val _statsByDistrictFlow = MutableStateFlow<List<StatsByDistrict>>(emptyList())
    val statsByDistrictFlow = _statsByDistrictFlow.asStateFlow()

    private val _statsByOverallFlow = MutableStateFlow(OverallStat.empty)
    val statsByOverallFlow = _statsByOverallFlow.asStateFlow()

    private val _countOfDataFlow = MutableStateFlow(0L)
    val countOfDataFlow = _countOfDataFlow.asStateFlow()

    private val _homesWithoutRatingCountFlow = MutableStateFlow(0L)
    val homesWithoutRatingCountFlow = _homesWithoutRatingCountFlow.asStateFlow()

    private val _countOfDistrictsFlow = MutableStateFlow(0)
    val countOfDistrictsFlow = _countOfDistrictsFlow.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        getStats()
    }

    private fun getStats() {
        coroutineScope.launch {
            _statsByDistrictFlow.emit(repository.getAllRatingByDistricts())
            _statsByOverallFlow.emit(repository.getOverallRating())
            _countOfDataFlow.emit(repository.getCountOfData())
            _homesWithoutRatingCountFlow.emit(repository.getHomesWithoutRating())
            _countOfDistrictsFlow.emit(repository.getCountOfDistricts().size)
        }
    }
}