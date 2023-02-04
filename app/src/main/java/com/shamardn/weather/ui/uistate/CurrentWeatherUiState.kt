package com.shamardn.weather.ui.uistate

data class CurrentWeatherUiState(
    val date: Long,
    val feelsLike: String,
    val humidity: String,
    val pressure: String,
    val temp: String,
//    val weatherCode: List<WeatherCode>,
    val windSpeed: String,
)
