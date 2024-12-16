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
import cmp_weatherapp.composeapp.generated.resources.moderate_or_heavy_rain_shower
import com.jpmobilelab.kmp.weatherapp.domain.model.CurrentWeather
import com.jpmobilelab.kmp.weatherapp.domain.model.Weather
import com.jpmobilelab.kmp.weatherapp.ui.composables.TransparentBox
import com.jpmobilelab.kmp.weatherapp.ui.weather.WeatherScreen
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun WeatherScreenPreview() {
    MaterialTheme {
        WeatherScreen(
            createMockWeather()
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
    temperature2m = 22.5f,
    apparentTemperature = 23.0f,
    isDay = 1,
    weatherCode = 2,
    relativeHumidity2m = 50.0f,
    windSpeed10m = 10.0f,
    weatherDescription = "Partly Cloudy",
    dayImageUrl = "https://openweathermap.org/img/wn/02d@2x.png",
    nightImageUrl = "https://openweathermap.org/img/wn/02n@2x.png"
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