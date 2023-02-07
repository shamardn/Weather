package com.shamardn.weather.ui.uistate

import com.shamardn.weather.ui.home.HomeItem

data class HomeUiState (
    val hourly: HomeItem = HomeItem.HourlyWeather(emptyList()),
    val daily: HomeItem = HomeItem.DailyWeather(emptyList()),
)
