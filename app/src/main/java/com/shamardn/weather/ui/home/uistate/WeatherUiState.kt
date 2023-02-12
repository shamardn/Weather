package com.shamardn.weather.ui.home.uistate

data class WeatherUiState(
    val isLoading: Boolean = false,
    val error : List<String> = emptyList(),
    val isFailed : Boolean = false,
    val isSuccess : Boolean = false,
    val isEmpty: Boolean = true,
    val currentWeatherUiState: CurrentWeatherUiState = CurrentWeatherUiState("","", "", "", "", "","","",""),
    val dailyUiState: List<DailyWeatherUiState> = emptyList(),
    val hourlyUiState: List<HourlyWeatherUiState> = emptyList(),
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val timezone: String = "",
    val timezoneOffset: Int = 0,
    )