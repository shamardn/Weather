package com.shamardn.weather.data.remote.response.dto


import com.google.gson.annotations.SerializedName
import com.shamardn.weather.data.remote.response.Temp
import com.shamardn.weather.data.remote.response.WeatherCode

data class DailyWeatherDTO(
    @SerializedName("dt")
    val date: Long?,
    @SerializedName("temp")
    val temp: Temp?,
    @SerializedName("weather")
    val weatherCode: List<WeatherCode>?,
)