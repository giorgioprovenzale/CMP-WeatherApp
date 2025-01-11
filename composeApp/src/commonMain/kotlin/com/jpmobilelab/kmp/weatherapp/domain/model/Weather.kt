package com.jpmobilelab.kmp.weatherapp.domain.model

data class Weather(
    val latitude: Float,
    val longitude: Float,
    val timezone: String,
    val timezoneAbbreviation: String,
    val elevation: Float,
    val current: CurrentWeather,
    val hourly: List<HourlyWeather> = emptyList(),
    val daily: List<DailyWeather> = emptyList(),
)
