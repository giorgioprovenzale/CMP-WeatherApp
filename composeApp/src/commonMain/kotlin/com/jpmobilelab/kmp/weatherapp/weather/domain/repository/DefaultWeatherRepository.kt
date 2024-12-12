package com.jpmobilelab.kmp.weatherapp.weather.domain.repository

import com.jpmobilelab.kmp.weatherapp.core.domain.DataError
import com.jpmobilelab.kmp.weatherapp.core.domain.Result
import com.jpmobilelab.kmp.weatherapp.core.domain.map
import com.jpmobilelab.kmp.weatherapp.weather.data.remote.RemoteWeatherDataSource
import com.jpmobilelab.kmp.weatherapp.weather.domain.mappers.toCurrentWeather
import com.jpmobilelab.kmp.weatherapp.weather.domain.mappers.toWeather
import com.jpmobilelab.kmp.weatherapp.weather.domain.model.Weather

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