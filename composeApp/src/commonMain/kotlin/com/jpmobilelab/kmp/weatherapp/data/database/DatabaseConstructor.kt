package com.jpmobilelab.kmp.weatherapp.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DatabaseConstructor : RoomDatabaseConstructor<Database> {
    override fun initialize(): Database
}