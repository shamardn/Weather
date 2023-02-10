package com.shamardn.weather.domain.model

data class HourlyWeather(
    val date: Long,
    val temp: Double,
    val icon: String,
)
