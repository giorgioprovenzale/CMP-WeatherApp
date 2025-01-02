package com.jpmobilelab.kmp.weatherapp

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object WeatherGraph : Route

    @Serializable
    data object WeatherHome : Route

    @Serializable
    data object SearchRoute : Route
}
