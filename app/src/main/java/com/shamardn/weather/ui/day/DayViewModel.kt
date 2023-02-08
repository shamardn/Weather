package com.shamardn.weather.ui.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamardn.weather.domain.usecase.FetchWeatherDetails
import com.shamardn.weather.ui.mapper.WeatherUiStateMapper
import com.shamardn.weather.ui.uistate.DayUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayViewModel @Inject constructor(
    private val fetchWeatherDetails: FetchWeatherDetails,
    private val weatherUiStateMapper: WeatherUiStateMapper,
): ViewModel() {
    private val _dayState = MutableStateFlow(DayUiState())
    val dayState = _dayState.asStateFlow()

    init {
        getDayData()
    }

    private fun getDayData() {
        _dayState.update { it.copy(isLoading = true) }
        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        viewModelScope.launch {
            try {
                val weatherDetails = weatherUiStateMapper.map(fetchWeatherDetails())
                _dayState.update {
                    it.copy(
                        dailyList = weatherDetails.dailyUiState,
                        isLoading = false,
                        error = emptyList()
                    )
                }
            } catch (e: Throwable) {
                val errors = dayState.value.error.toMutableList()
                errors.add(e.message.toString())
                _dayState.update {
                    it.copy(
                        error = errors,
                        isLoading = false,
                    )
                }
            }
        }
    }
}