package com.jpmobilelab.kmp.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAVORITE_TABLE_NAME = "favorite_table"

@Entity(tableName = FAVORITE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float,
)