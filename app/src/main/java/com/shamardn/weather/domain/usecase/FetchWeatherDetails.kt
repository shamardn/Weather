package com.shamardn.weather.domain.usecase

import com.shamardn.weather.data.repository.WeatherRepository
import com.shamardn.weather.domain.mapper.WeatherMapper
import com.shamardn.weather.domain.model.Weather
import javax.inject.Inject

class FetchWeatherDetails @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherMapper: WeatherMapper,
) {
    suspend operator fun invoke(lat: Double, lon: Double): Weather {
        return weatherMapper.map(weatherRepository.getWeatherDetails(lat, lon))
    }
}