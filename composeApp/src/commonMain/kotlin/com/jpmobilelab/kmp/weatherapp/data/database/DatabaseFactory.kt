package com.jpmobilelab.kmp.weatherapp.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<Database>
}