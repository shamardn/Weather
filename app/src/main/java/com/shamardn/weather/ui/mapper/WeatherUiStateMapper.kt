package com.shamardn.weather.ui.mapper

import com.shamardn.weather.domain.mapper.Mapper
import com.shamardn.weather.domain.model.Weather
import com.shamardn.weather.ui.uistate.WeatherUiState
import javax.inject.Inject

class WeatherUiStateMapper @Inject constructor(
    private val currentWeatherUiStateMapper: CurrentWeatherUiStateMapper,
    private val hourlyWeatherUiStateMapper: HourlyWeatherUiStateMapper,
    private val dailyWeatherUiStateMapper: DailyWeatherUiStateMapper,
): Mapper<Weather, WeatherUiState>() {
    override fun map(input: Weather): WeatherUiState {
        return WeatherUiState(
            currentWeatherUiState = currentWeatherUiStateMapper.map(input.currentWeather),
            dailyUiState = dailyWeatherUiStateMapper.mapList(input.daily),
            hourlyUiState = hourlyWeatherUiStateMapper.mapList(input.hourly),
            lat = input.lat,
            lon = input.lon,
            timezone = input.timezone,
            timezoneOffset = input.timezoneOffset,
        )
    }
}