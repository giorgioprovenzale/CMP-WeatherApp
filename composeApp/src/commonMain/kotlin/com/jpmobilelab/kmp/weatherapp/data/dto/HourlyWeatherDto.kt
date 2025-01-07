package com.jpmobilelab.kmp.weatherapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    @SerialName("time") val time: List<String>,
    @SerialName("temperature_2m") val temperature2m: List<Float>,
    @SerialName("precipitation_probability") val precipitationProbability: List<Int>,
    @SerialName("weather_code") val weatherCode: List<Int>,
    @SerialName("wind_speed_10m") val windSpeed10m: List<Float>,
    @SerialName("is_day") val isDay: List<Int>,
)
