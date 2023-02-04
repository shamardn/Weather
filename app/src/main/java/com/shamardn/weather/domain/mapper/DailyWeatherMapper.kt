package com.shamardn.weather.domain.mapper

import com.shamardn.weather.data.remote.response.dto.DailyWeatherDTO
import com.shamardn.weather.domain.model.DailyWeather
import javax.inject.Inject

class DailyWeatherMapper @Inject constructor(

): Mapper<DailyWeatherDTO, DailyWeather>() {
    override fun map(input: DailyWeatherDTO): DailyWeather {
        return DailyWeather(
            date = input.date ?: 0,
            humidity = input.humidity ?: 0,
            pressure = input.pressure ?: 0,
            windSpeed = input.windSpeed ?: 0.0,
            sunrise = input.sunrise ?: 0,
            sunset = input.sunset ?: 0,
        )
    }
}