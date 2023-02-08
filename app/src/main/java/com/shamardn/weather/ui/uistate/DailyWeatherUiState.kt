package com.shamardn.weather.ui.uistate

data class DailyWeatherUiState(

    val date: String = "",
//    val feelsLike: FeelsLike,
    val humidity: String = "",
    val pressure: String = "",
    val sunrise: String = "",
    val sunset: String = "",
//    val temp: Temp,
//    val weatherCode: List<WeatherCode>,
    val windSpeed: String = "",
)
