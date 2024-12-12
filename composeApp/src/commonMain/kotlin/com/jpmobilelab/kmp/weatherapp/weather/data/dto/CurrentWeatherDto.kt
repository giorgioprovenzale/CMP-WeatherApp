package com.jpmobilelab.kmp.weatherapp.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("temperature_2m") val temperature2m: Float,
    @SerialName("apparent_temperature") val apparentTemperature: Float,
    @SerialName("is_day") val isDay: Int,
    @SerialName("weather_code") val weatherCode: Int
)
