package com.shamardn.weather.ui.home.mapper

import com.shamardn.weather.domain.mapper.Mapper
import com.shamardn.weather.domain.model.DailyWeather
import com.shamardn.weather.ui.home.uistate.DailyWeatherUiState
import com.shamardn.weather.util.formatDate
import javax.inject.Inject

class DailyWeatherUiStateMapper @Inject constructor(): Mapper<DailyWeather, DailyWeatherUiState>() {
    override fun map(input: DailyWeather): DailyWeatherUiState {
        return DailyWeatherUiState(
            date = input.date.formatDate("MMM, dd"),
            day = input.date.formatDate("EEE"),
            max = "${input.max.toInt()}",
            min = "${input.min.toInt()}",
            icon = input.icon,
        )
    }
}