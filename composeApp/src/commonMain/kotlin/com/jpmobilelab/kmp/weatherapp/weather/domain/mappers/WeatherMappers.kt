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
    relativeHumidity2m = relativeHumidity2m,
    windSpeed10m = windSpeed10m,
    weatherDescription = getWeatherDescription(
        weatherCode, isDay.toString().toBoolean()
    ),
    dayImageUrl = getWeatherImageUrl(
        weatherCode, true
    ),
    nightImageUrl = getWeatherImageUrl(weatherCode, false)
)

private fun getWeatherImageUrl(code: Int, isDay: Boolean): String {
    return when (code) {
        0 -> if (isDay) "https://openweathermap.org/img/wn/01d@2x.png" else "https://openweathermap.org/img/wn/01n@2x.png"
        1 -> if (isDay) "https://openweathermap.org/img/wn/01d@2x.png" else "https://openweathermap.org/img/wn/01n@2x.png"
        2 -> if (isDay) "https://openweathermap.org/img/wn/02d@2x.png" else "https://openweathermap.org/img/wn/02n@2x.png"
        3 -> if (isDay) "https://openweathermap.org/img/wn/03d@2x.png" else "https://openweathermap.org/img/wn/03n@2x.png"
        45, 48 -> if (isDay) "https://openweathermap.org/img/wn/50d@2x.png" else "https://openweathermap.org/img/wn/50n@2x.png"
        51, 53, 55, 56, 57 -> if (isDay) "https://openweathermap.org/img/wn/09d@2x.png" else "https://openweathermap.org/img/wn/09n@2x.png"
        61, 63, 65, 66, 67 -> if (isDay) "https://openweathermap.org/img/wn/10d@2x.png" else "https://openweathermap.org/img/wn/10n@2x.png"
        71, 73, 75, 77 -> if (isDay) "https://openweathermap.org/img/wn/13d@2x.png" else "https://openweathermap.org/img/wn/13n@2x.png"
        80, 81, 82 -> if (isDay) "https://openweathermap.org/img/wn/09d@2x.png" else "https://openweathermap.org/img/wn/09n@2x.png"
        85, 86 -> if (isDay) "https://openweathermap.org/img/wn/13d@2x.png" else "https://openweathermap.org/img/wn/13n@2x.png"
        95, 96, 99 -> if (isDay) "https://openweathermap.org/img/wn/11d@2x.png" else "https://openweathermap.org/img/wn/11n@2x.png"
        else -> if (isDay) "https://openweathermap.org/img/wn/01d@2x.png" else "https://openweathermap.org/img/wn/01n@2x.png"
    }
}

private fun getWeatherDescription(code: Int, isDay: Boolean): String {
    return when (code) {
        0 -> if (isDay) "Sunny" else "Clear"
        1 -> if (isDay) "Mainly Sunny" else "Mainly Clear"
        2 -> "Partly Cloudy"
        3 -> "Cloudy"
        45 -> "Foggy"
        48 -> "Rime Fog"
        51 -> "Light Drizzle"
        53 -> "Drizzle"
        55 -> "Heavy Drizzle"
        56 -> "Light Freezing Drizzle"
        57 -> "Freezing Drizzle"
        61 -> "Light Rain"
        63 -> "Rain"
        65 -> "Heavy Rain"
        66 -> "Light Freezing Rain"
        67 -> "Freezing Rain"
        71 -> "Light Snow"
        73 -> "Snow"
        75 -> "Heavy Snow"
        77 -> "Snow Grains"
        80 -> "Light Showers"
        81 -> "Showers"
        82 -> "Heavy Showers"
        85 -> "Light Snow Showers"
        86 -> "Snow Showers"
        95 -> "Thunderstorm"
        96 -> "Light Thunderstorms With Hail"
        99 -> "Thunderstorm With Hail"
        else -> if (isDay) "Unknown" else "Unknown"
    }
}