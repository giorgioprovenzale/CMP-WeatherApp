package com.jpmobilelab.kmp.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmobilelab.kmp.weatherapp.domain.core.onSuccess
import com.jpmobilelab.kmp.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state = _state
        .onStart { fetchWeather() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    private suspend fun fetchWeather() {
        _state.update { WeatherState.Loading }
        weatherRepository.getCurrentWeatherData(latitude = 36.925682F, longitude = 14.730745F)
            .onSuccess { weather ->
                _state.update { WeatherState.Content(location = "Current Location", weather = weather) }
            }
    }
}
