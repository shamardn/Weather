package com.shamardn.weather.domain.mapper

import com.shamardn.weather.data.remote.response.dto.DailyWeatherDTO
import com.shamardn.weather.domain.model.DailyWeather
import javax.inject.Inject

class DailyWeatherMapper @Inject constructor(

): Mapper<DailyWeatherDTO, DailyWeather>() {
    override fun map(input: DailyWeatherDTO): DailyWeather {
        return DailyWeather(
            date = input.date ?: 0,
            day = input.date ?: 0,
            max = input.temp?.max ?: 0.0,
            min = input.temp?.min ?: 0.0,
            icon =  input.weatherCode?.get(0)?.icon ?: "",
        )
    }
}