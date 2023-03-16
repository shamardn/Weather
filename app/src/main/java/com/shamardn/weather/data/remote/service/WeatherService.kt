package com.shamardn.weather.data.remote.service

import com.shamardn.weather.data.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("data/2.5/onecall")
    suspend fun getWeatherDetails(
    ): WeatherResponse

    @GET("data/2.5/onecall/{lat}/{lon}")
    suspend fun getWeatherDetails(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
    ): WeatherResponse
}