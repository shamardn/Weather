package com.shamardn.weather.data.remote.response.dto


import com.google.gson.annotations.SerializedName
import com.shamardn.weather.data.remote.response.WeatherCode

data class CurrentWeatherDTO(
    @SerializedName("dt")
    val date: Long?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("weather")
    val weatherCode: List<WeatherCode?>?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)