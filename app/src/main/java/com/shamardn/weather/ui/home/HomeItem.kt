package com.shamardn.weather.ui.home

import com.shamardn.weather.ui.uistate.DailyWeatherUiState
import com.shamardn.weather.ui.uistate.HourlyWeatherUiState

sealed class HomeItem(val priority: Int) {

    data class HourlyWeather(val items: List<HourlyWeatherUiState>, val type: HomeItemsType = HomeItemsType.HOURS) : HomeItem(2)

    data class DailyWeather(val items: List<DailyWeatherUiState>, val type: HomeItemsType = HomeItemsType.DAYS) : HomeItem(3)

}