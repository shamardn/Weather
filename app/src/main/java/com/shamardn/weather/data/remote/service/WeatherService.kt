package com.shamardn.weather.data.remote.service

import com.shamardn.weather.data.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/onecall")
    suspend fun getWeatherDetails(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
    ): WeatherResponse
}