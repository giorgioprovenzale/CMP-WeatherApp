package com.jpmobilelab.kmp.weatherapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("latitude") val latitude: Float,
    @SerialName("longitude") val longitude: Float,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerialName("elevation") val elevation: Float,
    @SerialName("current") val current: CurrentWeatherDto,
    @SerialName("hourly") val hourly: HourlyWeatherDto? = null,
)
