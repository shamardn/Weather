package com.shamardn.weather.ui.uistate

data class HourlyWeatherUiState(
    val date: Long,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
//    val weatherCode: List<WeatherCode>,
    val windSpeed: Double,
)
