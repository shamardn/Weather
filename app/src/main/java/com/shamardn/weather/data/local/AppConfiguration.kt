package com.shamardn.weather.data.local

import javax.inject.Inject

interface AppConfiguration {

    suspend fun getLongitude(): Double?

    suspend fun saveLongitude(value: Double)

    suspend fun getLatitude(): Double?

    suspend fun saveLatitude(value: Double)
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

    companion object DataStorePreferencesKeys {
        const val LONGITUDE_KEY = "longitude_key"
        const val LATITUDE_KEY = "latitude_key"
    }
}