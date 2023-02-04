package com.shamardn.weather.data.remote.response


import com.google.gson.annotations.SerializedName
import com.shamardn.weather.data.remote.response.dto.CurrentWeatherDTO
import com.shamardn.weather.data.remote.response.dto.DailyWeatherDTO
import com.shamardn.weather.data.remote.response.dto.HourlyWeatherDTO

data class WeatherResponse(
    @SerializedName("current")
    val currentWeatherDTO: CurrentWeatherDTO?,
    @SerializedName("daily")
    val daily: List<DailyWeatherDTO>?,
    @SerializedName("hourly")
    val hourly: List<HourlyWeatherDTO>?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?
)