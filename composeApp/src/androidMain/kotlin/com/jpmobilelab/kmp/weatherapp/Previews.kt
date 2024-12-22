package com.jpmobilelab.kmp.weatherapp

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.current_location
import cmp_weatherapp.composeapp.generated.resources.error_location_permission_denied
import cmp_weatherapp.composeapp.generated.resources.error_location_permission_denied_forever
import cmp_weatherapp.composeapp.generated.resources.ic_0_clear_day
import cmp_weatherapp.composeapp.generated.resources.ic_0_clear_night
import cmp_weatherapp.composeapp.generated.resources.moderate_or_heavy_rain_shower
import com.jpmobilelab.kmp.weatherapp.domain.model.CurrentWeather
import com.jpmobilelab.kmp.weatherapp.domain.model.Weather
import com.jpmobilelab.kmp.weatherapp.ui.composables.TransparentBox
import com.jpmobilelab.kmp.weatherapp.ui.core.UiText
import com.jpmobilelab.kmp.weatherapp.ui.weather.WeatherScreen
import com.jpmobilelab.kmp.weatherapp.ui.weather.WeatherState
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun WeatherScreenPreview() {
    MaterialTheme {
        WeatherScreen(
            state = WeatherState.Content(
                location = UiText.StringResourceId(Res.string.current_location), weather = createMockWeather()
            ),
            onGetLocationClick = {}
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun WeatherScreenLoadingPreview() {
    MaterialTheme {
        WeatherScreen(
            state = WeatherState.Loading,
            onGetLocationClick = {}
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun WeatherScreenErrorPreview() {
    MaterialTheme {
        WeatherScreen(
            state = WeatherState.Error(
                message = UiText.StringResourceId(Res.string.error_location_permission_denied_forever),
                showButton = false
            ),
            onGetLocationClick = {}
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun WeatherScreenErrorWithButtonPreview() {
    MaterialTheme {
        WeatherScreen(
            state = WeatherState.Error(
                message = UiText.StringResourceId(Res.string.error_location_permission_denied),
                showButton = true,
            ),
            onGetLocationClick = {}
        )
    }
}

fun createMockWeather() = Weather(
    latitude = 45.4642f,
    longitude = 9.1900f,
    timezone = "Europe/Rome",
    timezoneAbbreviation = "CET",
    elevation = 122.0f,
    current = createMockCurrentWeather()
)

private fun createMockCurrentWeather() = CurrentWeather(
    time = LocalDateTime.parse("2024-12-20T15:45"),
    temperature2m = 22.5f,
    apparentTemperature = 23.0f,
    isDay = true,
    weatherCode = 2,
    relativeHumidity2m = 50.0f,
    windSpeed10m = 10.0f,
    weatherDescription = "Light Thunderstorms With Hail",
    dayDrawableResource = Res.drawable.ic_0_clear_day,
    nightDrawableResource = Res.drawable.ic_0_clear_night,
)

@Composable
fun WeatherTransparentBox() {
    MaterialTheme {
        TransparentBox {
            Row {
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Wed 16", style = MaterialTheme.typography.titleSmall)
                    Image(painterResource(Res.drawable.moderate_or_heavy_rain_shower), null)
                    Text(text = "22°", style = MaterialTheme.typography.headlineMedium)
                    Text(text = "1-5", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Km/h", style = MaterialTheme.typography.labelSmall)
                }
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Wed 16", style = MaterialTheme.typography.titleSmall)
                    Image(painterResource(Res.drawable.moderate_or_heavy_rain_shower), null)
                    Text(text = "22°", style = MaterialTheme.typography.headlineMedium)
                    Text(text = "1-5", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Km/h", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}