package com.jpmobilelab.kmp.weatherapp.domain.mappers

import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.ic_0_clear_day
import cmp_weatherapp.composeapp.generated.resources.ic_0_clear_night
import cmp_weatherapp.composeapp.generated.resources.ic_1_mainly_clear_day
import cmp_weatherapp.composeapp.generated.resources.ic_1_mainly_clear_night
import cmp_weatherapp.composeapp.generated.resources.ic_2_partly_cloudy_day
import cmp_weatherapp.composeapp.generated.resources.ic_2_partly_cloudy_night
import cmp_weatherapp.composeapp.generated.resources.ic_3_cloudy_day
import cmp_weatherapp.composeapp.generated.resources.ic_3_cloudy_night
import cmp_weatherapp.composeapp.generated.resources.ic_45_fog_day
import cmp_weatherapp.composeapp.generated.resources.ic_45_fog_night
import cmp_weatherapp.composeapp.generated.resources.ic_48_freezing_fog_day
import cmp_weatherapp.composeapp.generated.resources.ic_48_freezing_fog_night
import cmp_weatherapp.composeapp.generated.resources.ic_51_light_drizzle_day
import cmp_weatherapp.composeapp.generated.resources.ic_51_light_drizzle_night
import cmp_weatherapp.composeapp.generated.resources.ic_53_moderate_drizzle_day
import cmp_weatherapp.composeapp.generated.resources.ic_53_moderate_drizzle_night
import cmp_weatherapp.composeapp.generated.resources.ic_55_dense_drizzle_day
import cmp_weatherapp.composeapp.generated.resources.ic_55_dense_drizzle_night
import cmp_weatherapp.composeapp.generated.resources.ic_56_light_freezing_drizzle_day
import cmp_weatherapp.composeapp.generated.resources.ic_56_light_freezing_drizzle_night
import cmp_weatherapp.composeapp.generated.resources.ic_57_dense_freezing_drizzle_day
import cmp_weatherapp.composeapp.generated.resources.ic_57_dense_freezing_drizzle_night
import cmp_weatherapp.composeapp.generated.resources.ic_61_slight_rain_day
import cmp_weatherapp.composeapp.generated.resources.ic_61_slight_rain_night
import cmp_weatherapp.composeapp.generated.resources.ic_63_moderate_rain_day
import cmp_weatherapp.composeapp.generated.resources.ic_63_moderate_rain_night
import cmp_weatherapp.composeapp.generated.resources.ic_65_heavy_rain_day
import cmp_weatherapp.composeapp.generated.resources.ic_65_heavy_rain_night
import cmp_weatherapp.composeapp.generated.resources.ic_67_heavy_freezing_rain_day
import cmp_weatherapp.composeapp.generated.resources.ic_67_heavy_freezing_rain_night
import cmp_weatherapp.composeapp.generated.resources.ic_71_light_snow_day
import cmp_weatherapp.composeapp.generated.resources.ic_71_light_snow_night
import cmp_weatherapp.composeapp.generated.resources.ic_73_moderate_snow_day
import cmp_weatherapp.composeapp.generated.resources.ic_73_moderate_snow_night
import cmp_weatherapp.composeapp.generated.resources.ic_75_heavy_snow_day
import cmp_weatherapp.composeapp.generated.resources.ic_75_heavy_snow_night
import cmp_weatherapp.composeapp.generated.resources.ic_77_snow_grains_day
import cmp_weatherapp.composeapp.generated.resources.ic_77_snow_grains_night
import cmp_weatherapp.composeapp.generated.resources.ic_80_slight_rain_shower_day
import cmp_weatherapp.composeapp.generated.resources.ic_80_slight_rain_shower_night
import cmp_weatherapp.composeapp.generated.resources.ic_81_moderate_rain_shower_day
import cmp_weatherapp.composeapp.generated.resources.ic_81_moderate_rain_shower_night
import cmp_weatherapp.composeapp.generated.resources.ic_82_violent_rain_shower_day
import cmp_weatherapp.composeapp.generated.resources.ic_82_violent_rain_shower_night
import cmp_weatherapp.composeapp.generated.resources.ic_85_light_snow_showers_day
import cmp_weatherapp.composeapp.generated.resources.ic_85_light_snow_showers_night
import cmp_weatherapp.composeapp.generated.resources.ic_86_heavy_snow_showers_day
import cmp_weatherapp.composeapp.generated.resources.ic_86_heavy_snow_showers_night
import cmp_weatherapp.composeapp.generated.resources.ic_95_slight_thunderstorm_day
import cmp_weatherapp.composeapp.generated.resources.ic_95_slight_thunderstorm_night
import cmp_weatherapp.composeapp.generated.resources.ic_96_thunderstorm_with_slight_hail_day
import cmp_weatherapp.composeapp.generated.resources.ic_96_thunderstorm_with_slight_hail_night
import cmp_weatherapp.composeapp.generated.resources.ic_99_thunderstorm_with_heavy_hail_day
import cmp_weatherapp.composeapp.generated.resources.ic_99_thunderstorm_with_heavy_hail_night
import com.jpmobilelab.kmp.weatherapp.data.dto.CurrentWeatherDto
import com.jpmobilelab.kmp.weatherapp.data.dto.WeatherDto
import com.jpmobilelab.kmp.weatherapp.domain.model.CurrentWeather
import com.jpmobilelab.kmp.weatherapp.domain.model.Weather
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.DrawableResource

fun WeatherDto.toWeather(): Weather = Weather(
    latitude = latitude,
    longitude = longitude,
    timezone = timezone,
    timezoneAbbreviation = timezoneAbbreviation,
    elevation = elevation,
)

fun CurrentWeatherDto.toCurrentWeather(): CurrentWeather = CurrentWeather(
    time = LocalDateTime.parse(time),
    temperature2m = temperature2m,
    apparentTemperature = apparentTemperature,
    isDay = isDay.toBoolean(),
    weatherCode = weatherCode,
    relativeHumidity2m = relativeHumidity2m,
    windSpeed10m = windSpeed10m,
    precipitationProbability = precipitationProbability,
    weatherDescription = getWeatherDescription(
        weatherCode, isDay.toBoolean()
    ),
    dayDrawableResource = getDrawableResource(
        weatherCode, true
    ),
    nightDrawableResource = getDrawableResource(weatherCode, false)
)

private fun Int.toBoolean(): Boolean = this == 1

private fun getDrawableResource(code: Int, isDay: Boolean): DrawableResource {
    return when (code) {
        0 -> if (isDay) Res.drawable.ic_0_clear_day else Res.drawable.ic_0_clear_night
        1 -> if (isDay) Res.drawable.ic_1_mainly_clear_day else Res.drawable.ic_1_mainly_clear_night
        2 -> if (isDay) Res.drawable.ic_2_partly_cloudy_day else Res.drawable.ic_2_partly_cloudy_night
        3 -> if (isDay) Res.drawable.ic_3_cloudy_day else Res.drawable.ic_3_cloudy_night
        45 -> if (isDay) Res.drawable.ic_45_fog_day else Res.drawable.ic_45_fog_night
        48 -> if (isDay) Res.drawable.ic_48_freezing_fog_day else Res.drawable.ic_48_freezing_fog_night
        51 -> if (isDay) Res.drawable.ic_51_light_drizzle_day else Res.drawable.ic_51_light_drizzle_night
        53 -> if (isDay) Res.drawable.ic_53_moderate_drizzle_day else Res.drawable.ic_53_moderate_drizzle_night
        55 -> if (isDay) Res.drawable.ic_55_dense_drizzle_day else Res.drawable.ic_55_dense_drizzle_night
        56 -> if (isDay) Res.drawable.ic_56_light_freezing_drizzle_day else Res.drawable.ic_56_light_freezing_drizzle_night
        57 -> if (isDay) Res.drawable.ic_57_dense_freezing_drizzle_day else Res.drawable.ic_57_dense_freezing_drizzle_night
        61 -> if (isDay) Res.drawable.ic_61_slight_rain_day else Res.drawable.ic_61_slight_rain_night
        63 -> if (isDay) Res.drawable.ic_63_moderate_rain_day else Res.drawable.ic_63_moderate_rain_night
        65 -> if (isDay) Res.drawable.ic_65_heavy_rain_day else Res.drawable.ic_65_heavy_rain_night
        67 -> if (isDay) Res.drawable.ic_67_heavy_freezing_rain_day else Res.drawable.ic_67_heavy_freezing_rain_night
        71 -> if (isDay) Res.drawable.ic_71_light_snow_day else Res.drawable.ic_71_light_snow_night
        73 -> if (isDay) Res.drawable.ic_73_moderate_snow_day else Res.drawable.ic_73_moderate_snow_night
        75 -> if (isDay) Res.drawable.ic_75_heavy_snow_day else Res.drawable.ic_75_heavy_snow_night
        77 -> if (isDay) Res.drawable.ic_77_snow_grains_day else Res.drawable.ic_77_snow_grains_night
        80 -> if (isDay) Res.drawable.ic_80_slight_rain_shower_day else Res.drawable.ic_80_slight_rain_shower_night
        81 -> if (isDay) Res.drawable.ic_81_moderate_rain_shower_day else Res.drawable.ic_81_moderate_rain_shower_night
        82 -> if (isDay) Res.drawable.ic_82_violent_rain_shower_day else Res.drawable.ic_82_violent_rain_shower_night
        85 -> if (isDay) Res.drawable.ic_85_light_snow_showers_day else Res.drawable.ic_85_light_snow_showers_night
        86 -> if (isDay) Res.drawable.ic_86_heavy_snow_showers_day else Res.drawable.ic_86_heavy_snow_showers_night
        95 -> if (isDay) Res.drawable.ic_95_slight_thunderstorm_day else Res.drawable.ic_95_slight_thunderstorm_night
        96 -> if (isDay) Res.drawable.ic_96_thunderstorm_with_slight_hail_day else Res.drawable.ic_96_thunderstorm_with_slight_hail_night
        99 -> if (isDay) Res.drawable.ic_99_thunderstorm_with_heavy_hail_day else Res.drawable.ic_99_thunderstorm_with_heavy_hail_night
        else -> if (isDay) Res.drawable.ic_0_clear_day else Res.drawable.ic_0_clear_night
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
        else -> "Unknown"
    }
}