package com.shamardn.weather.ui.uistate

data class HomeUiState (
    val dailyList: List<DailyWeatherUiState> = emptyList(),
    val isLoading:Boolean = false,
    val error : List<String> = emptyList(),
)
