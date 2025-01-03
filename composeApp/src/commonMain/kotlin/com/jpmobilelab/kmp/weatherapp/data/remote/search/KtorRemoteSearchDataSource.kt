package com.jpmobilelab.kmp.weatherapp.data.remote.search

import BASE_PATH
import com.jpmobilelab.kmp.weatherapp.data.core.safeCall
import com.jpmobilelab.kmp.weatherapp.data.dto.LocationsDto
import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteSearchDataSource(
    private val httpClient: HttpClient
) : RemoteSearchDataSource {

    override suspend fun searchLocationsData(
        query: String,
    ): Result<LocationsDto, DataError.Remote> {
        return safeCall<LocationsDto> {
            httpClient.get(
                urlString = "https://geocoding-$BASE_PATH/search"
            ) {
                parameter("name", query)
            }
        }
    }
}