package com.shamardn.weather.domain.mapper

import com.shamardn.weather.data.remote.response.WeatherResponse
import com.shamardn.weather.domain.model.CurrentWeather
import com.shamardn.weather.domain.model.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor(
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val hourlyWeatherMapper: HourlyWeatherMapper,
    private val dailyWeatherMapper: DailyWeatherMapper,
): Mapper<WeatherResponse, Weather>(){
    override fun map(input: WeatherResponse): Weather {
        return Weather(
            currentWeather = input.currentWeatherDTO?.let { currentWeatherMapper.map(it) } ?: CurrentWeather(0,0.0,0,0,0.0,0.0),
            daily = dailyWeatherMapper.mapList(input.daily!!),
            hourly = hourlyWeatherMapper.mapList(input.hourly!!),
            lat = input.lat ?: 0.0,
            lon = input.lon ?: 0.0,
            timezone = input.timezone ?: "",
            timezoneOffset = input.timezoneOffset ?: 0,
        )
    }

}

/*

class HourlyWeatherMapper @Inject constructor(

): Mapper<HourlyWeatherDTO, HourlyWeather>() {
    override fun map(input: HourlyWeatherDTO): HourlyWeather {
        return HourlyWeather(
            date = input.date ?: 0,
            feelsLike = input.feelsLike ?: 0.0,
            humidity = input.humidity ?: 0,
            pressure = input.pressure ?: 0,
            temp = input.temp ?: 0.0,
            windSpeed = input.windSpeed ?: 0.0,
        )
    }
}
 */
