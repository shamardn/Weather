package com.shamardn.weather.domain.mapper

import com.shamardn.weather.data.remote.response.dto.CurrentWeatherDTO
import com.shamardn.weather.domain.model.CurrentWeather
import javax.inject.Inject

class CurrentWeatherMapper @Inject constructor(

): Mapper<CurrentWeatherDTO, CurrentWeather>() {
    override fun map(input: CurrentWeatherDTO): CurrentWeather {
        return CurrentWeather(
            date = input.date ?: 0,
            feelsLike = input.feelsLike ?: 0.0,
            humidity = input.humidity ?: 0,
            pressure = input.pressure ?: 0,
            temp = input.temp ?: 0.0,
            windSpeed = input.windSpeed ?: 0.0,
        )
    }
}