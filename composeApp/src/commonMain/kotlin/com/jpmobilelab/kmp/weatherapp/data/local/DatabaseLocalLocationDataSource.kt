package com.jpmobilelab.kmp.weatherapp.data.local

import com.jpmobilelab.kmp.weatherapp.data.database.LocationDao
import com.jpmobilelab.kmp.weatherapp.data.database.LocationEntity
import kotlinx.coroutines.flow.Flow

class DatabaseLocalLocationDataSource(
    private val locationDao: LocationDao
) : LocalLocationDataSource {
    override suspend fun saveLocation(location: LocationEntity) {
        locationDao.upsert(location)
    }

    override fun observeLocations(): Flow<List<LocationEntity>> =
        locationDao.observeLocations()

    override suspend fun deleteLocation(id: Int) {
        locationDao.deleteLocation(id)
    }
}
