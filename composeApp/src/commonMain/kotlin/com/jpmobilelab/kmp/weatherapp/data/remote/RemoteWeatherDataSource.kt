package com.jpmobilelab.kmp.weatherapp.data.remote

import com.jpmobilelab.kmp.weatherapp.data.dto.WeatherDto
import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result

interface RemoteWeatherDataSource {

    suspend fun getCurrentWeatherData(latitude: Float, longitude: Float): Result<WeatherDto, DataError.Remote>
}