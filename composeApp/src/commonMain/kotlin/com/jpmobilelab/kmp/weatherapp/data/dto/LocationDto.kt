package com.jpmobilelab.kmp.weatherapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("latitude") val latitude: Float,
    @SerialName("longitude") val longitude: Float,
    @SerialName("timezone") val timezone: String,
    @SerialName("country_code") val countryCode: String,
    @SerialName("country") val country: String? = null,
    @SerialName("population") val population: Int? = null,
    @SerialName("admin1") val admin1: String? = null,
    @SerialName("admin2") val admin2: String? = null,
    @SerialName("admin3") val admin3: String? = null,
    @SerialName("admin4") val admin4: String? = null,
)
