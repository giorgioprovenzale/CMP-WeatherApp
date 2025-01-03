package com.jpmobilelab.kmp.weatherapp.domain.model

data class Location(
    val id: Int,
    val name: String,
    val subName: String,
    val latitude: Float,
    val longitude: Float,
    val timezone: String,
    val country: String,
    val flagUrl: String
)
