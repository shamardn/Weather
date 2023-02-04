package com.shamardn.weather.domain.model

data class HourlyWeather(
    val date: Long,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
//    val weatherCode: List<WeatherCode>,
    val windSpeed: Double,
)
