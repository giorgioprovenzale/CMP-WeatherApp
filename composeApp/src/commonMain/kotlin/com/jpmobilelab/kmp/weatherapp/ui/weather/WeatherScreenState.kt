package com.jpmobilelab.kmp.weatherapp.ui.weather

import com.jpmobilelab.kmp.weatherapp.domain.model.Weather

sealed interface WeatherState {
    data object Loading : WeatherState
    data class Content(val location: String, val weather: Weather) : WeatherState
}
