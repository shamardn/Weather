package com.shamardn.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamardn.weather.domain.usecase.FetchWeatherDetails
import com.shamardn.weather.ui.home.mapper.WeatherUiStateMapper
import com.shamardn.weather.ui.home.uistate.WeatherUiState
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
    private val _homeUiState = MutableStateFlow(WeatherUiState())
    val homeState = _homeUiState.asStateFlow()
    init {
        getHomeData()
    }

    private fun getHomeData() {
        _homeUiState.update { it.copy(isLoading = true) }
        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        viewModelScope.launch {
            try {
                val weatherDetails = weatherUiStateMapper.map(fetchWeatherDetails())
                _homeUiState.update {
                    it.copy(
                        currentWeatherUiState = weatherDetails.currentWeatherUiState,
                        hourlyUiState = weatherDetails.hourlyUiState,
                        dailyUiState = weatherDetails.dailyUiState,
                        isSuccess = true,
                        isLoading = false,
                        isFailed = false,
                    )
                }

            } catch (e: Throwable) {
                onError(e.message.toString())
            }
        }
    }

    private fun onError(message: String) {
        val errors = _homeUiState.value.error.toMutableList()
        errors.add(message)
        _homeUiState.update {
            it.copy(
                error = errors,
                isLoading = false,
                isFailed = true,
            )
        }
    }
}