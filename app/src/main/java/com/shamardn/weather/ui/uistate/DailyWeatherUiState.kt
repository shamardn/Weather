package com.shamardn.weather.ui.uistate

data class DailyWeatherUiState(
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
