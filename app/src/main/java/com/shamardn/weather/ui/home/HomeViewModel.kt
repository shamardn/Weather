package com.shamardn.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamardn.weather.domain.usecase.FetchWeatherDetails
import com.shamardn.weather.ui.mapper.WeatherUiStateMapper
import com.shamardn.weather.ui.uistate.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchWeatherDetails: FetchWeatherDetails,
    private val weatherUiStateMapper: WeatherUiStateMapper,
) : ViewModel() {
    private val _state = MutableStateFlow(WeatherUiState())
    val state = _state.asStateFlow()

    init {
        getHomeData()
    }

    private fun getHomeData() {
        _state.update { it.copy(isLoading = true) }
        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        viewModelScope.launch {
            try {
                val weatherDetails = weatherUiStateMapper.map(fetchWeatherDetails())
                _state.update {
                    it.copy(
                        currentWeatherUiState = weatherDetails.currentWeatherUiState,
                        dailyUiState = weatherDetails.dailyUiState,
                        hourlyUiState = weatherDetails.hourlyUiState,
                        lat = weatherDetails.lat,
                        lon = weatherDetails.lon,
                        timezone = weatherDetails.timezone,
                        timezoneOffset = weatherDetails.timezoneOffset,
                        isLoading = false,
                        isError = false,
                    )
                }
            } catch (e: Throwable) {
                _state.update {
                    it.copy(
                        isError = true,
                        isLoading = false
                    )
                }
            }
        }
    }
}