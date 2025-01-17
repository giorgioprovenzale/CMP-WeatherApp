package com.jpmobilelab.kmp.weatherapp.domain.mappers

import com.jpmobilelab.kmp.weatherapp.data.database.LocationEntity
import com.jpmobilelab.kmp.weatherapp.data.dto.LocationDto
import com.jpmobilelab.kmp.weatherapp.domain.model.Location

fun LocationDto.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        subName = listOfNotNull(admin1, admin2, admin3, admin4).joinToString(", "),
        latitude = latitude,
        longitude = longitude,
        timezone = timezone,
        country = country.orEmpty(),
        flagUrl = "https://hatscripts.github.io/circle-flags/flags/${countryCode.lowercase()}.svg"
    )
}

fun Location.toLocationEntity(): LocationEntity = LocationEntity(
    id = id,
    name = name,
    subName = subName,
    latitude = latitude,
    longitude = longitude,
    timezone = timezone,
    country = country,
    flagUrl = flagUrl
)