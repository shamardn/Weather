package com.shamardn.weather.domain.model

data class CurrentWeather(
    val date: Long,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val description: String,
    val windSpeed: Double,
    val sunrise: Long,
    val sunset: Long,
)
