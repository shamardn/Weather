package com.shamardn.weather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamardn.weather.data.local.AppConfiguration
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
    private val appConfiguration: AppConfiguration,
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(WeatherUiState())
    val homeState = _homeUiState.asStateFlow()

    private val _timeZone = MutableLiveData<String>()
    val timeZone: LiveData<String> = _timeZone

    private val _latitude = MutableLiveData<Double>()
    val latitude: LiveData<Double> = _latitude

    private val _longitude = MutableLiveData<Double>()
    val longitude: LiveData<Double> = _longitude

    init {
        getHomeData()
        getLocation()
        getTimeZone()
    }

    private fun getLocation() {
        viewModelScope.launch {
            _longitude.postValue(appConfiguration.getLongitude())
            _latitude.postValue(appConfiguration.getLatitude())
        }
    }

    suspend fun saveLongitude(longitude: Double) {
        appConfiguration.saveLongitude(longitude)
    }

    suspend fun saveLatitude(latitude: Double) {
        appConfiguration.saveLatitude(latitude)
    }

    private suspend fun saveTimeZone(city: String) {
        _timeZone.postValue(city)
        appConfiguration.saveTimeZone(city)
    }

     private fun getTimeZone(){
         viewModelScope.launch {
             if (appConfiguration.getTimeZone() != null) {
                 _timeZone.postValue(appConfiguration.getTimeZone())
             }
         }
    }

    private fun getHomeData() {
        _homeUiState.update { it.copy(isLoading = true) }
        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        viewModelScope.launch {
            try {
                val weatherDetails = weatherUiStateMapper.map(fetchWeatherDetails())
                saveTimeZone(weatherDetails.timezone)
                _homeUiState.update {
                    it.copy(
                        currentWeatherUiState = weatherDetails.currentWeatherUiState,
                        hourlyUiState = weatherDetails.hourlyUiState,
                        dailyUiState = weatherDetails.dailyUiState,
                        timezone = weatherDetails.timezone,
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