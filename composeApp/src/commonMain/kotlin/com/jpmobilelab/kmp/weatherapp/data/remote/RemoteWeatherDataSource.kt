package com.jpmobilelab.kmp.weatherapp.data.remote

import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result
import com.jpmobilelab.kmp.weatherapp.weather.data.dto.WeatherDto

interface RemoteWeatherDataSource {

    suspend fun getCurrentWeatherData(latitude: Float, longitude: Float): Result<WeatherDto, DataError.Remote>
}