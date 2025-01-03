package com.jpmobilelab.kmp.weatherapp.domain.repository

import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result
import com.jpmobilelab.kmp.weatherapp.domain.model.Location

interface LocationRepository {
    suspend fun searchLocation(query: String): Result<List<Location>, DataError.Remote>
}