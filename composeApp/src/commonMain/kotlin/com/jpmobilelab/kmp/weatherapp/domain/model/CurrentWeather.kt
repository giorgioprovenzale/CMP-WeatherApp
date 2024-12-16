package com.jpmobilelab.kmp.weatherapp.domain.model

data class CurrentWeather(
    val temperature2m: Float,
    val apparentTemperature: Float,
    val isDay: Int,
    val weatherCode: Int,
    val relativeHumidity2m: Float,
    val windSpeed10m: Float,
    val weatherDescription: String,
    val dayImageUrl: String,
    val nightImageUrl: String
)
