package com.shamardn.weather.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamardn.weather.data.local.AppConfiguration
import com.shamardn.weather.util.Event
import com.shamardn.weather.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val appConfiguration: AppConfiguration,
) : ViewModel() {

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>> get() = _navigateToHome

    init {
    }

    suspend fun saveLongitude(longitude: Double) {
        appConfiguration.saveLongitude(longitude)
    }

    suspend fun saveLatitude(latitude: Double) {
        appConfiguration.saveLatitude(latitude)
    }

    fun onStartClick() {
        _navigateToHome.postEvent(true)
    }
}