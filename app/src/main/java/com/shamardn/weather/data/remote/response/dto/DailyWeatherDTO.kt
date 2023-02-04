package com.shamardn.weather.data.remote.response.dto


import com.google.gson.annotations.SerializedName
import com.shamardn.weather.data.remote.response.FeelsLike
import com.shamardn.weather.data.remote.response.Temp
import com.shamardn.weather.data.remote.response.WeatherCode

data class DailyWeatherDTO(
    @SerializedName("dt")
    val date: Long?,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("sunrise")
    val sunrise: Long?,
    @SerializedName("sunset")
    val sunset: Long?,
    @SerializedName("temp")
    val temp: Temp?,
    @SerializedName("weather")
    val weatherCode: List<WeatherCode>?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)