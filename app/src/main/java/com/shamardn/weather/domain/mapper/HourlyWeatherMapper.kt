package com.shamardn.weather.domain.mapper

import com.shamardn.weather.data.remote.response.dto.HourlyWeatherDTO
import com.shamardn.weather.domain.model.HourlyWeather
import javax.inject.Inject

class HourlyWeatherMapper @Inject constructor(

): Mapper<HourlyWeatherDTO, HourlyWeather>() {
    override fun map(input: HourlyWeatherDTO): HourlyWeather {
        return HourlyWeather(
            date = input.date ?: 0,
            temp = input.temp ?: 0.0,
            icon =  input.weatherCode?.get(0)?.icon ?: ""
        )
    }
}