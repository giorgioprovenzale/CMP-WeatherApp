package com.jpmobilelab.kmp.weatherapp.domain.model

import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.DrawableResource

data class HourlyWeather(
    val time: LocalDateTime,
    val temperature2m: Float,
    val weatherCode: Int,
    val precipitationProbability: Int,
    val windSpeed10m: Float,
    val isDay: Boolean,
    val weatherDescription: String,
    val dayDrawableResource: DrawableResource,
    val nightDrawableResource: DrawableResource
) {
    fun getDrawableResource() = if (isDay) dayDrawableResource else nightDrawableResource
}
