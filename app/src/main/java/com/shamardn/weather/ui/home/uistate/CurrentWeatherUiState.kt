package com.shamardn.weather.ui.home.uistate

data class CurrentWeatherUiState(
    val timeZone: String,
    val date: String,
    val feelsLike: String,
    val humidity: String,
    val pressure: String,
    val temp: String,
    val description: String,
    val windSpeed: String,
    val sunrise: String,
    val sunset: String,
)
