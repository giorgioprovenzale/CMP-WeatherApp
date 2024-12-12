package com.jpmobilelab.kmp.weatherapp.weather.domain.mappers

import com.jpmobilelab.kmp.weatherapp.weather.data.dto.CurrentWeatherDto
import com.jpmobilelab.kmp.weatherapp.weather.data.dto.WeatherDto
import com.jpmobilelab.kmp.weatherapp.weather.domain.model.CurrentWeather
import com.jpmobilelab.kmp.weatherapp.weather.domain.model.Weather

fun WeatherDto.toWeather(): Weather = Weather(
    latitude = latitude,
    longitude = longitude,
    timezone = timezone,
    timezoneAbbreviation = timezoneAbbreviation,
    elevation = elevation,
    current = current?.toCurrentWeather()
)

fun CurrentWeatherDto.toCurrentWeather(): CurrentWeather = CurrentWeather(
    temperature2m = temperature2m,
    apparentTemperature = apparentTemperature,
    isDay = isDay,
    weatherCode = weatherCode
)