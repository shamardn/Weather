package com.shamardn.weather.data.repository

import com.shamardn.weather.data.remote.response.WeatherResponse

interface WeatherRepository {
    suspend fun getWeatherDetails(lat: Double, lon: Double): WeatherResponse
}