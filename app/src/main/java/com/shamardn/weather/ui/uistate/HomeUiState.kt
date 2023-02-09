package com.shamardn.weather.ui.uistate

data class HomeUiState (
    val dailyList: List<DailyWeatherUiState> = emptyList(),
    val header: CurrentWeatherUiState = CurrentWeatherUiState("","","","","",""),
    val hourList: List<HourlyWeatherUiState> = emptyList(),
    val details: DailyWeatherUiState = DailyWeatherUiState("","","","","",""),
    val isLoading:Boolean = false,
    val error : List<String> = emptyList(),
)
