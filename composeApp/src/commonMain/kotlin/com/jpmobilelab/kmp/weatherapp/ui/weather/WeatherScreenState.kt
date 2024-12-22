package com.jpmobilelab.kmp.weatherapp.ui.weather

import com.jpmobilelab.kmp.weatherapp.domain.model.Weather
import com.jpmobilelab.kmp.weatherapp.ui.core.UiText

sealed interface WeatherState {
    data object Loading : WeatherState
    data class Content(val location: UiText, val weather: Weather) : WeatherState
    data class Error(val message: UiText, val showButton: Boolean) : WeatherState
}
