package com.jpmobilelab.kmp.weatherapp.ui

import androidx.compose.runtime.Composable
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.difference_in_days
import cmp_weatherapp.composeapp.generated.resources.difference_in_hours
import cmp_weatherapp.composeapp.generated.resources.difference_in_minutes
import cmp_weatherapp.composeapp.generated.resources.difference_in_weeks
import cmp_weatherapp.composeapp.generated.resources.just_now
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun formatTimeDifference(targetDateTime: LocalDateTime, timeZone: TimeZone): String {
    val targetInstant = targetDateTime.toInstant(timeZone)
    val nowInstant = Clock.System.now().toLocalDateTime(timeZone).toInstant(timeZone)

    val duration = nowInstant - targetInstant

    return when {
        duration.inWholeSeconds < 60 -> stringResource(Res.string.just_now)
        duration.inWholeMinutes < 60 -> pluralStringResource(
            Res.plurals.difference_in_minutes,
            duration.inWholeMinutes.toInt(),
            duration.inWholeMinutes
        )

        duration.inWholeHours < 24 -> pluralStringResource(
            Res.plurals.difference_in_hours,
            duration.inWholeHours.toInt(),
            duration.inWholeHours
        )

        duration.inWholeDays < 7 -> pluralStringResource(Res.plurals.difference_in_days, duration.inWholeDays.toInt(), duration.inWholeDays)
        else -> {
            val days = duration.inWholeDays
            val weeks = days / 7
            if (weeks < 4) pluralStringResource(Res.plurals.difference_in_weeks, weeks.toInt(), weeks)
            else pluralStringResource(Res.plurals.difference_in_days, (weeks / 4).toInt(), (weeks / 4).toInt())
        }
    }
}