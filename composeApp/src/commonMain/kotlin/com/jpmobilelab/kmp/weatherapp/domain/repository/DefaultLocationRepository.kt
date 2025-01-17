package com.jpmobilelab.kmp.weatherapp.domain.repository

import com.jpmobilelab.kmp.weatherapp.data.local.LocalLocationDataSource
import com.jpmobilelab.kmp.weatherapp.data.remote.search.RemoteSearchDataSource
import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result
import com.jpmobilelab.kmp.weatherapp.domain.core.map
import com.jpmobilelab.kmp.weatherapp.domain.mappers.toLocation
import com.jpmobilelab.kmp.weatherapp.domain.mappers.toLocationEntity
import com.jpmobilelab.kmp.weatherapp.domain.model.Location

class DefaultLocationRepository(
    private val remoteSearchDataSource: RemoteSearchDataSource,
    private val localLocationDataSource: LocalLocationDataSource
) : LocationRepository {

    override suspend fun searchLocation(query: String): Result<List<Location>, DataError.Remote> {
        return remoteSearchDataSource.searchLocationsData(query).map { locationsDto ->
            locationsDto.results
                .sortedByDescending { it.population }
                .map { it.toLocation() }
        }
    }

    override suspend fun saveLocation(location: Location) {
        localLocationDataSource.saveLocation(location.toLocationEntity())
    }
}
