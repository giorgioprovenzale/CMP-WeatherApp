package com.jpmobilelab.kmp.weatherapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
@ConstructedBy(DatabaseConstructor::class)
abstract class Database : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao

    companion object {
        const val DB_NAME = "database.db"
    }
}