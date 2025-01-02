package com.jpmobilelab.kmp.weatherapp.domain.model

import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.DrawableResource

data class CurrentWeather(
    val time: LocalDateTime,
    val temperature2m: Float,
    val apparentTemperature: Float,
    val isDay: Boolean,
    val weatherCode: Int,
    val relativeHumidity2m: Float,
    val windSpeed10m: Float,
    val precipitationProbability: Int,
    val weatherDescription: String,
    val dayDrawableResource: DrawableResource,
    val nightDrawableResource: DrawableResource
) {
    fun getDrawableResource() = if (isDay) dayDrawableResource else nightDrawableResource
}
