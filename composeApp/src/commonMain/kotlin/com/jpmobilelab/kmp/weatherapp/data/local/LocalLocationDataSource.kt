package com.jpmobilelab.kmp.weatherapp.data.local

import com.jpmobilelab.kmp.weatherapp.data.database.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocalLocationDataSource {

    suspend fun saveLocation(location: LocationEntity)
    fun observeLocations(): Flow<List<LocationEntity>>
    suspend fun deleteLocation(id: Int)
}