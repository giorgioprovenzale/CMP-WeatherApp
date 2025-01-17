package com.jpmobilelab.kmp.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val LOCATION_TABLE_NAME = "location_table"

@Entity(tableName = LOCATION_TABLE_NAME)
data class LocationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val subName: String,
    val latitude: Float,
    val longitude: Float,
    val timezone: String,
    val country: String,
    val flagUrl: String
)