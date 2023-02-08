package com.shamardn.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamardn.weather.domain.usecase.FetchWeatherDetails
import com.shamardn.weather.ui.mapper.WeatherUiStateMapper
import com.shamardn.weather.ui.uistate.HomeUiState
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
    private val _homeState = MutableStateFlow(HomeUiState())
    val homeState = _homeState.asStateFlow()

    init {
        getHomeData()
    }

    private fun getHomeData() {
        _homeState.update { it.copy(isLoading = true) }
        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        viewModelScope.launch {
            try {
                val weatherDetails = weatherUiStateMapper.map(fetchWeatherDetails())
                _homeState.update {
                    it.copy(
                        dailyList = weatherDetails.dailyUiState,
                        header = weatherDetails.currentWeatherUiState,
                        isLoading = false,
                        error = emptyList()
                    )
                }
            } catch (e: Throwable) {
                val errors = homeState.value.error.toMutableList()
                errors.add(e.message.toString())
                _homeState.update {
                    it.copy(
                        error = errors,
                        isLoading = false,
                    )
                }
            }
        }
    }
}