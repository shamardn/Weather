package com.shamardn.weather.data.remote.service

import com.shamardn.weather.data.remote.response.WeatherResponse
import retrofit2.http.GET

interface WeatherService {

    @GET("data/2.5/onecall")
    suspend fun getWeatherDetails(
    ): WeatherResponse

}