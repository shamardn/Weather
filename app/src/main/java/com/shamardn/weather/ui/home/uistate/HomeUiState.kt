package com.shamardn.weather.ui.home.uistate


sealed class HomeUiState{
    object Success: HomeUiState()
    data class Error(val message: String): HomeUiState()
    object Loading: HomeUiState()
    object Empty: HomeUiState()
}
