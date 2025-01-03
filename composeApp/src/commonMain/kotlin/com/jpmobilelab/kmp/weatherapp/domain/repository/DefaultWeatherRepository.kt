package com.jpmobilelab.kmp.weatherapp.domain.repository

import com.jpmobilelab.kmp.weatherapp.data.remote.weather.RemoteWeatherDataSource
import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result
import com.jpmobilelab.kmp.weatherapp.domain.core.map
import com.jpmobilelab.kmp.weatherapp.domain.mappers.toCurrentWeather
import com.jpmobilelab.kmp.weatherapp.domain.mappers.toWeather
import com.jpmobilelab.kmp.weatherapp.domain.model.Weather

class DefaultWeatherRepository(
    private val remoteWeatherDataSource: RemoteWeatherDataSource,
) : WeatherRepository {

    override suspend fun getCurrentWeatherData(latitude: Float, longitude: Float): Result<Weather, DataError.Remote> {

        return remoteWeatherDataSource.getCurrentWeatherData(
            latitude = latitude,
            longitude = longitude
        ).map { weatherDto ->
            weatherDto.toWeather().copy(current = weatherDto.current?.toCurrentWeather())
        }
    }

}