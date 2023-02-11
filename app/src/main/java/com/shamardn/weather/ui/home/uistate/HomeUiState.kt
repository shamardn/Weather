package com.shamardn.weather.ui.home.uistate

data class HomeUiState (
    val dailyList: List<DailyWeatherUiState> = emptyList(),
    val header: CurrentWeatherUiState = CurrentWeatherUiState("","","","","", "","","",""),
    val hourList: List<HourlyWeatherUiState> = emptyList(),
    val details: CurrentWeatherUiState = CurrentWeatherUiState("","","","","","", "","",""),
    val isLoading: Boolean = false,
    val error : List<String> = emptyList(),
)
