package com.shamardn.weather.data.remote.response


import com.google.gson.annotations.SerializedName

data class WeatherCode(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?
)