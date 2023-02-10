package com.shamardn.weather.data.remote.response.dto


import com.google.gson.annotations.SerializedName
import com.shamardn.weather.data.remote.response.WeatherCode

data class HourlyWeatherDTO(
    @SerializedName("dt")
    val date: Long?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("weather")
    val weatherCode: List<WeatherCode>?,
)