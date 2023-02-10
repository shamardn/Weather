package com.shamardn.weather.domain.model

data class DailyWeather(
    val date: Long,
    val day: Long,
    val max: Double,
    val min: Double,
    val icon: String,
)
