package com.jpmobilelab.kmp.weatherapp.domain.model

import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.DrawableResource

data class DailyWeather(
    val time: LocalDate,
    val temperature2mMax: Float,
    val temperature2mMin: Float,
    val weatherCode: Int,
    val precipitationProbabilityMax: Int,
    val windSpeed10mMax: Float,
    val weatherDescription: String,
    val weatherDrawableResource: DrawableResource,
)
