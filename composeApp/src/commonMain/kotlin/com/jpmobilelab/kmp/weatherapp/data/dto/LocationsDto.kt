package com.jpmobilelab.kmp.weatherapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationsDto(
    @SerialName("results") val results: List<LocationDto> = emptyList()
)
