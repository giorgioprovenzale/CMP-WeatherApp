package com.jpmobilelab.kmp.weatherapp.data.remote.weather

import BASE_PATH
import com.jpmobilelab.kmp.weatherapp.data.core.safeCall
import com.jpmobilelab.kmp.weatherapp.data.dto.WeatherDto
import com.jpmobilelab.kmp.weatherapp.domain.core.DataError
import com.jpmobilelab.kmp.weatherapp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteWeatherDataSource(
    private val httpClient: HttpClient
) : RemoteWeatherDataSource {

    override suspend fun getCurrentWeatherData(
        latitude: Float,
        longitude: Float
    ): Result<WeatherDto, DataError.Remote> {
        return safeCall<WeatherDto> {
            httpClient.get(
                urlString = "https://$BASE_PATH/forecast"
            ) {
                parameter("latitude", latitude)
                parameter("longitude", longitude)
                parameter("timezone", "auto")
                parameter(
                    "current",
                    "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,weather_code,wind_speed_10m,precipitation_probability"
                )
                parameter(
                    "hourly",
                    "temperature_2m,precipitation_probability,weather_code,wind_speed_10m,is_day"
                )
                parameter(
                    "daily",
                    "weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max,wind_speed_10m_max"
                )
            }
        }
    }
}