package com.shamardn.weather.ui.location

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamardn.weather.data.local.AppConfiguration
import com.shamardn.weather.util.FitchLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val appConfiguration: AppConfiguration,
) : ViewModel() {

    private var _city = MutableLiveData("Cairo")
    val city: LiveData<String> = _city

    init {

    }

    fun findLocation(activity: Activity) {
        FitchLocation.getCurrentLocation(activity)
    }

    suspend fun saveLongitude(longitude: Double) {
        appConfiguration.saveLongitude(longitude)
    }

    suspend fun saveLatitude(latitude: Double) {
        appConfiguration.saveLatitude(latitude)
    }


}