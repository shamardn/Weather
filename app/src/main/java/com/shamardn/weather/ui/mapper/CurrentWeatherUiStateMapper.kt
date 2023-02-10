package com.shamardn.weather.ui.mapper

import com.shamardn.weather.domain.mapper.Mapper
import com.shamardn.weather.domain.model.CurrentWeather
import com.shamardn.weather.ui.uistate.CurrentWeatherUiState
import com.shamardn.weather.util.formatDate
import javax.inject.Inject

class CurrentWeatherUiStateMapper @Inject constructor(): Mapper<CurrentWeather, CurrentWeatherUiState>() {
    override fun map(input: CurrentWeather): CurrentWeatherUiState {
        return CurrentWeatherUiState(
            date = input.date.formatDate("h:mm"),
            feelsLike = "${input.feelsLike.toInt()}°C",
            humidity = "${input.humidity}%",
            pressure = "${input.pressure} hPa",
            temp = input.temp.toInt().toString(),
            windSpeed = "${input.windSpeed} km/h",
            description = input.description,
            sunrise = input.sunrise.formatDate("h:mm"),
            sunset = input.sunset.formatDate("H:mm"),
        )
    }
}