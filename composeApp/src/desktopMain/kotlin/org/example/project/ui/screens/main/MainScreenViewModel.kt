package org.example.project.ui.screens.main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.DatabaseRepository
import org.example.project.domain.models.StatsByDistrict

class MainScreenViewModel(private val repository: DatabaseRepository) {
    private val _statsByDistrictFlow = MutableStateFlow<List<StatsByDistrict>>(emptyList())
    val statsByDistrictFlow = _statsByDistrictFlow.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        getStatsByDistrict()
    }

    private fun getStatsByDistrict() {
        coroutineScope.launch {
            _statsByDistrictFlow.emit(repository.getAllRatingByDistricts())
        }
    }
}