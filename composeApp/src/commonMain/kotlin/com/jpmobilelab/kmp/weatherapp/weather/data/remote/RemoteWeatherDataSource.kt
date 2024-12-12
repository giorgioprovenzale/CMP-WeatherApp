package com.jpmobilelab.kmp.weatherapp.weather.data.remote

import com.jpmobilelab.kmp.weatherapp.core.domain.DataError
import com.jpmobilelab.kmp.weatherapp.core.domain.Result
import com.jpmobilelab.kmp.weatherapp.weather.data.dto.WeatherDto

interface RemoteWeatherDataSource {

    suspend fun getCurrentWeatherData(latitude: Float, longitude: Float): Result<WeatherDto, DataError.Remote>
}