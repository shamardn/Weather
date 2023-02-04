package com.shamardn.weather.domain.mapper

import com.shamardn.weather.data.remote.response.dto.HourlyWeatherDTO
import com.shamardn.weather.domain.model.HourlyWeather
import javax.inject.Inject

class HourlyWeatherMapper @Inject constructor(

): Mapper<HourlyWeatherDTO, HourlyWeather>() {
    override fun map(input: HourlyWeatherDTO): HourlyWeather {
        return HourlyWeather(
            date = input.date ?: 0,
            feelsLike = input.feelsLike ?: 0.0,
            humidity = input.humidity ?: 0,
            pressure = input.pressure ?: 0,
            temp = input.temp ?: 0.0,
            windSpeed = input.windSpeed ?: 0.0,
        )
    }
}