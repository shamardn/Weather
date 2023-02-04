package com.shamardn.weather.domain.model

data class Weather(
    val currentWeather: CurrentWeather,
    val daily: List<DailyWeather>,
    val hourly: List<HourlyWeather>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezoneOffset: Int,
)
