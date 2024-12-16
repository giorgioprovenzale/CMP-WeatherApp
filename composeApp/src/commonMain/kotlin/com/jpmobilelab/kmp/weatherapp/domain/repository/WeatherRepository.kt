package com.jpmobilelab.kmp.weatherapp.domain.repository

import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result
import com.jpmobilelab.kmp.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeatherData(latitude: Float, longitude: Float): Result<Weather, DataError.Remote>
}