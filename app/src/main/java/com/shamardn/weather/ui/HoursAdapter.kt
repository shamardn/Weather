package com.shamardn.weather.ui

import com.shamardn.weather.R
import com.shamardn.weather.ui.base.BaseAdapter
import com.shamardn.weather.ui.uistate.HourlyWeatherUiState

class HoursAdapter(items: List<HourlyWeatherUiState>):BaseAdapter<HourlyWeatherUiState>(items) {
    override val layoutID = R.layout.hourly_item
}