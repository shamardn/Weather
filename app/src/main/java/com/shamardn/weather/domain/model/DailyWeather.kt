package com.shamardn.weather.domain.model

data class DailyWeather(
    val date: Long,
//    val feelsLike: FeelsLike,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Long,
    val sunset: Long,
//    val temp: Temp,
//    val weatherCode: List<WeatherCode>,
    val windSpeed: Double,
)
