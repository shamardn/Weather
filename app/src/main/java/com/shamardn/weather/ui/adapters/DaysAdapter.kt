package com.shamardn.weather.ui.adapters


import com.shamardn.weather.R
import com.shamardn.weather.ui.base.BaseAdapter
import com.shamardn.weather.ui.home.uistate.DailyWeatherUiState


class DaysAdapter(items: List<DailyWeatherUiState>): BaseAdapter<DailyWeatherUiState>(items) {
    override val layoutID = R.layout.daily_item
}
