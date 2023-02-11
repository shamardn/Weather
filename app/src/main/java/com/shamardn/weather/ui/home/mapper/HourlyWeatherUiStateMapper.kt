package com.shamardn.weather.ui.home.mapper

import com.shamardn.weather.domain.mapper.Mapper
import com.shamardn.weather.domain.model.HourlyWeather
import com.shamardn.weather.ui.home.uistate.HourlyWeatherUiState
import com.shamardn.weather.util.formatDate
import javax.inject.Inject

class HourlyWeatherUiStateMapper @Inject constructor() :
    Mapper<HourlyWeather, HourlyWeatherUiState>() {
    override fun map(input: HourlyWeather): HourlyWeatherUiState {
        return HourlyWeatherUiState(
            date = input.date.formatDate("HH:mm"),
            temp = "${input.temp}",
            icon = input.icon,
        )
    }
}