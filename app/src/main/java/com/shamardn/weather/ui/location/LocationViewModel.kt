package com.shamardn.weather.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.shamardn.weather.domain.usecase.FetchWeatherDetails
import com.shamardn.weather.ui.home.mapper.WeatherUiStateMapper
import com.shamardn.weather.ui.home.uistate.WeatherUiState
import com.shamardn.weather.util.FitchLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val fitchLocation: FitchLocation,
) : ViewModel() {

    private var _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    init {
        fitchLocation.getCurrentLocation()
    }

    private fun getCityName(){
        _city.value = fitchLocation.getCityName()
    }
}