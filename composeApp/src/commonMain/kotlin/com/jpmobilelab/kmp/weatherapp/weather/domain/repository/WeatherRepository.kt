package com.jpmobilelab.kmp.weatherapp.weather.domain.repository

import com.jpmobilelab.kmp.weatherapp.core.domain.DataError
import com.jpmobilelab.kmp.weatherapp.core.domain.Result
import com.jpmobilelab.kmp.weatherapp.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeatherData(latitude: Float, longitude: Float): Result<Weather, DataError.Remote>
}