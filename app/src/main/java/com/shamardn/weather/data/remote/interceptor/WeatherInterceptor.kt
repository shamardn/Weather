package com.shamardn.weather.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import com.shamardn.weather.BuildConfig.API_KEY
import javax.inject.Inject

class WeatherInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(APP_ID, API_KEY)
            .addQueryParameter(UNITS, UNIT_METRIC)
            .build()
        return chain.proceed((chain.request().newBuilder().url(response).build()))
    }

    companion object{
        const val APP_ID = "appid"
        const val LAT = "lat"
        const val LON = "lon"

        const val CAIRO_LAT = "30.0333"
        const val CAIRO_LON = "31.2333"
        const val UNITS = "units"
        const val UNIT_METRIC = "metric"
    }
}