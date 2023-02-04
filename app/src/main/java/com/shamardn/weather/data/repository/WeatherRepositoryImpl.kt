package com.shamardn.weather.data.repository

import android.util.Log
import com.shamardn.weather.data.remote.response.WeatherResponse
import com.shamardn.weather.data.remote.service.WeatherService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
): WeatherRepository {
    override suspend fun getWeatherDetails(): WeatherResponse {
        Log.i("wsh","in WeatherRepositoryImpl:${weatherService.getWeatherDetails()}")
        return weatherService.getWeatherDetails()
    }
}