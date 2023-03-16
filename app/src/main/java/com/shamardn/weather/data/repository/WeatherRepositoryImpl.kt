package com.shamardn.weather.data.repository

import com.shamardn.weather.data.remote.response.WeatherResponse
import com.shamardn.weather.data.remote.service.WeatherService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
): WeatherRepository {
    override suspend fun getWeatherDetails(lat: Double, lon: Double): WeatherResponse {
        return weatherService.getWeatherDetails(lat, lon)
    }
}