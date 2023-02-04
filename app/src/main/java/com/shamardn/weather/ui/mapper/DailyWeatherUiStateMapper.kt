package com.shamardn.weather.ui.mapper

import com.shamardn.weather.domain.mapper.Mapper
import com.shamardn.weather.domain.model.DailyWeather
import com.shamardn.weather.ui.uistate.DailyWeatherUiState
import javax.inject.Inject

class DailyWeatherUiStateMapper @Inject constructor(): Mapper<DailyWeather, DailyWeatherUiState>() {
    override fun map(input: DailyWeather): DailyWeatherUiState {
        return DailyWeatherUiState(
            date = input.date,
            humidity = input.humidity,
            pressure = input.pressure,
            sunrise = input.sunrise,
            sunset = input.sunset,
            windSpeed = input.windSpeed,
        )
    }
}