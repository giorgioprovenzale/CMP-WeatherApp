package com.jpmobilelab.kmp.weatherapp.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmobilelab.kmp.weatherapp.core.domain.onSuccess
import com.jpmobilelab.kmp.weatherapp.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state
        .onStart {
            weatherRepository.getCurrentWeatherData(latitude = 36.925682F, longitude = 14.730745F).onSuccess { weather ->
                _state.update { it.copy(weather = weather) }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )
}