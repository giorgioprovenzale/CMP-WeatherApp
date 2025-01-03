package com.jpmobilelab.kmp.weatherapp.data.remote.search

import com.jpmobilelab.kmp.weatherapp.data.dto.LocationsDto
import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result

interface RemoteSearchDataSource {

    suspend fun searchLocationsData(query: String): Result<LocationsDto, DataError.Remote>
}