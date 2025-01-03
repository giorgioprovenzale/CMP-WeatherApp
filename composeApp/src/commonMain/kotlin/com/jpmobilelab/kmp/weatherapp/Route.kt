package com.jpmobilelab.kmp.weatherapp

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object WeatherGraph : Route

    @Serializable
    data class WeatherHome(
        val latitude: Float? = null,
        val longitude: Float? = null,
        val name: String? = null
    ) : Route

    @Serializable
    data object SearchRoute : Route
}
