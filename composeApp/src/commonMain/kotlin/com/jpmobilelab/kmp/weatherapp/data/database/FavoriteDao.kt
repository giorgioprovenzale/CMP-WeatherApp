package com.jpmobilelab.kmp.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Upsert
    suspend fun upsert(book: FavoriteEntity)

    @Query("SELECT * FROM $FAVORITE_TABLE_NAME")
    fun getFavoriteLocations(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM $FAVORITE_TABLE_NAME WHERE id = :id")
    suspend fun deleteLocation(id: String)
}