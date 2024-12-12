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
)

fun CurrentWeatherDto.toCurrentWeather(): CurrentWeather = CurrentWeather(
    temperature2m = temperature2m,
    apparentTemperature = apparentTemperature,
    isDay = isDay,
    weatherCode = weatherCode,
    dayImageUrl = getWeatherImageUrl(weatherCode, true),
    nightImageUrl = getWeatherImageUrl(weatherCode, false)
)

private fun getWeatherImageUrl(code: Int, isDay: Boolean): String {
    return when (code) {
        0 -> if (isDay) "http://openweathermap.org/img/wn/01d@2x.png" else "http://openweathermap.org/img/wn/01n@2x.png"
        1 -> if (isDay) "http://openweathermap.org/img/wn/01d@2x.png" else "http://openweathermap.org/img/wn/01n@2x.png"
        2 -> if (isDay) "http://openweathermap.org/img/wn/02d@2x.png" else "http://openweathermap.org/img/wn/02n@2x.png"
        3 -> if (isDay) "http://openweathermap.org/img/wn/03d@2x.png" else "http://openweathermap.org/img/wn/03n@2x.png"
        45, 48 -> if (isDay) "http://openweathermap.org/img/wn/50d@2x.png" else "http://openweathermap.org/img/wn/50n@2x.png"
        51, 53, 55, 56, 57 -> if (isDay) "http://openweathermap.org/img/wn/09d@2x.png" else "http://openweathermap.org/img/wn/09n@2x.png"
        61, 63, 65, 66, 67 -> if (isDay) "http://openweathermap.org/img/wn/10d@2x.png" else "http://openweathermap.org/img/wn/10n@2x.png"
        71, 73, 75, 77 -> if (isDay) "http://openweathermap.org/img/wn/13d@2x.png" else "http://openweathermap.org/img/wn/13n@2x.png"
        80, 81, 82 -> if (isDay) "http://openweathermap.org/img/wn/09d@2x.png" else "http://openweathermap.org/img/wn/09n@2x.png"
        85, 86 -> if (isDay) "http://openweathermap.org/img/wn/13d@2x.png" else "http://openweathermap.org/img/wn/13n@2x.png"
        95, 96, 99 -> if (isDay) "http://openweathermap.org/img/wn/11d@2x.png" else "http://openweathermap.org/img/wn/11n@2x.png"
        else -> if (isDay) "http://openweathermap.org/img/wn/01d@2x.png" else "http://openweathermap.org/img/wn/01n@2x.png"
    }
}