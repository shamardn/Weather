package com.shamardn.weather.ui.day


import com.shamardn.weather.R
import com.shamardn.weather.ui.base.BaseAdapter
import com.shamardn.weather.ui.uistate.DailyWeatherUiState


class DaysAdapter(items: List<DailyWeatherUiState>): BaseAdapter<DailyWeatherUiState>(items) {
    override val layoutID = R.layout.daily_item
}
