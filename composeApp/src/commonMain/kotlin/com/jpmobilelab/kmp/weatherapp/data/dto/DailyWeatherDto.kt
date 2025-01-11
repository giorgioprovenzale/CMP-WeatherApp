package com.jpmobilelab.kmp.weatherapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("time") val time: List<String>,
    @SerialName("temperature_2m_max") val temperature2mMax: List<Float>,
    @SerialName("temperature_2m_min") val temperature2mMin: List<Float>,
    @SerialName("precipitation_probability_max") val precipitationProbabilityMax: List<Int>,
    @SerialName("weather_code") val weatherCode: List<Int>,
    @SerialName("wind_speed_10m_max") val windSpeed10mMax: List<Float>,
)
