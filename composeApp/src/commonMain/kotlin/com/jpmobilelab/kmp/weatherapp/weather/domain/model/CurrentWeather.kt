package com.jpmobilelab.kmp.weatherapp.weather.domain.model

data class CurrentWeather(
    val temperature2m: Float,
    val apparentTemperature: Float,
    val isDay: Int,
    val weatherCode: Int
)
