package com.jpmobilelab.kmp.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Upsert
    suspend fun upsert(book: LocationEntity)

    @Query("SELECT * FROM $LOCATION_TABLE_NAME")
    fun observeLocations(): Flow<List<LocationEntity>>

    @Query("DELETE FROM $LOCATION_TABLE_NAME WHERE id = :id")
    suspend fun deleteLocation(id: Int)
}