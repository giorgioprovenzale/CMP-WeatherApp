package com.jpmobilelab.kmp.weatherapp.weather.data.remote

import com.jpmobilelab.kmp.weatherapp.core.data.safeCall
import com.jpmobilelab.kmp.weatherapp.core.domain.DataError
import com.jpmobilelab.kmp.weatherapp.core.domain.Result
import com.jpmobilelab.kmp.weatherapp.weather.data.dto.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://api.open-meteo.com/v1"

class KtorRemoteWeatherDataSource(
    private val httpClient: HttpClient
) : RemoteWeatherDataSource {

    override suspend fun getCurrentWeatherData(
        latitude: Float,
        longitude: Float
    ): Result<WeatherDto, DataError.Remote> {
        return safeCall<WeatherDto> {
            httpClient.get(
                urlString = "$BASE_URL/forecast"
            ) {
                parameter("latitude", latitude)
                parameter("longitude", longitude)
                parameter("timezone", "auto")
                parameter(
                    "current",
                    "temperature_2m,apparent_temperature,is_day,weather_code"
                )
            }
        }
    }
}