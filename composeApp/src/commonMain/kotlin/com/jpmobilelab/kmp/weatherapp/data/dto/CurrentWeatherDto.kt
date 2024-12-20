package com.jpmobilelab.kmp.weatherapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("time") val time: String,
    @SerialName("temperature_2m") val temperature2m: Float,
    @SerialName("apparent_temperature") val apparentTemperature: Float,
    @SerialName("is_day") val isDay: Int,
    @SerialName("weather_code") val weatherCode: Int,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: Float,
    @SerialName("wind_speed_10m") val windSpeed10m: Float,
)
