package com.shamardn.weather.ui.mapper

import com.shamardn.weather.domain.mapper.Mapper
import com.shamardn.weather.domain.model.HourlyWeather
import com.shamardn.weather.ui.uistate.HourlyWeatherUiState
import javax.inject.Inject

class HourlyWeatherUiStateMapper @Inject constructor(): Mapper<HourlyWeather, HourlyWeatherUiState>() {
    override fun map(input: HourlyWeather): HourlyWeatherUiState {
        return HourlyWeatherUiState(
            date = input.date,
            feelsLike = input.feelsLike,
            humidity = input.humidity,
            pressure = input.pressure,
            temp = input.temp,
            windSpeed = input.windSpeed,
        )
    }
}