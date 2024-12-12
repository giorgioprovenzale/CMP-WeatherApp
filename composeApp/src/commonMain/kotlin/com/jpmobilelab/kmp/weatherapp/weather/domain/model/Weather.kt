package com.jpmobilelab.kmp.weatherapp.weather.domain.model

data class Weather(
    val latitude: Float,
    val longitude: Float,
    val timezone: String,
    val timezoneAbbreviation: String,
    val elevation: Float,
    val current: CurrentWeather? = null
)
