package com.shamardn.weather.util

import com.shamardn.weather.ui.day.DayItem
import com.shamardn.weather.ui.day.DayItemType
import com.shamardn.weather.ui.home.HomeItem
import com.shamardn.weather.ui.home.HomeItemType
import com.shamardn.weather.ui.uistate.DailyWeatherUiState

fun DailyWeatherUiState.toDayItem() : DayItem<Any> {
    return DayItem(this, DayItemType.TYPE_DAY)
}

fun DailyWeatherUiState.toHomeItem() : HomeItem<Any> {
    return HomeItem(this, HomeItemType.TYPE_DAY)
}