package com.shamardn.weather.data.local

import javax.inject.Inject

interface AppConfiguration {

    suspend fun getLongitude(): Double?

    suspend fun saveLongitude(value: Double)

    suspend fun getLatitude(): Double?

    suspend fun saveLatitude(value: Double)

    suspend fun getTimeZone(): String?

    suspend fun saveTimeZone(value: String)
}

class AppConfigurator @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
    ): AppConfiguration {
    override suspend fun getLongitude(): Double? {
        return dataStorePreferences.readDouble(LONGITUDE_KEY)
    }

    override suspend fun saveLongitude(value: Double) {
        dataStorePreferences.writeDouble(LONGITUDE_KEY, value)
    }

    override suspend fun getLatitude(): Double? {
        return dataStorePreferences.readDouble(LATITUDE_KEY)
    }

    override suspend fun saveLatitude(value: Double) {
        dataStorePreferences.writeDouble(LATITUDE_KEY, value)
    }

    override suspend fun saveTimeZone(value: String) {
        dataStorePreferences.writeString(TIME_ZONE_KEY, value)
    }

    override suspend fun getTimeZone(): String? {
        return dataStorePreferences.readString(TIME_ZONE_KEY)
    }

    companion object DataStorePreferencesKeys {
        const val LONGITUDE_KEY = "longitude_key"
        const val LATITUDE_KEY = "latitude_key"
        const val TIME_ZONE_KEY = "time_zone_key"
    }
}