package com.jpmobilelab.kmp.weatherapp.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.feels_like
import cmp_weatherapp.composeapp.generated.resources.humidity
import cmp_weatherapp.composeapp.generated.resources.ic_feels_like
import cmp_weatherapp.composeapp.generated.resources.ic_humidity
import cmp_weatherapp.composeapp.generated.resources.ic_wind
import cmp_weatherapp.composeapp.generated.resources.last_update
import cmp_weatherapp.composeapp.generated.resources.wind
import com.jpmobilelab.kmp.weatherapp.domain.model.Weather
import com.jpmobilelab.kmp.weatherapp.theme.spacing_0_5x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_1x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_2x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_3x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_8x
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradient
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradientStartingColor
import com.jpmobilelab.kmp.weatherapp.theme.weatherIconsSizeLarge
import com.jpmobilelab.kmp.weatherapp.ui.composables.WeatherValueWithLabelAndIcon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WeatherScreenRoot(
    viewModel: WeatherViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    WeatherScreen(
        weather = state.weather,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(weather: Weather?) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = verticalGradientStartingColor,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = { }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(verticalGradient)
                .padding(innerPadding)
                .padding(horizontal = spacing_2x)
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            WeatherMainProperties(weather)

            Spacer(Modifier.height(spacing_8x))

            WeatherProperties(weather)
        }
    }
}

@Composable
private fun WeatherMainProperties(weather: Weather?) = weather?.current?.let {
    Text(
        text = "Current Location", // todo: get from API
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = spacing_1x),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(end = spacing_1x)
                .weight(0.6f),
            text = weather.current.weatherDescription,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Column(
            modifier = Modifier
                .weight(0.4f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${stringResource(Res.string.last_update)}:",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = weather.current.getDate(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(start = spacing_3x)) {
            Text(
                text = weather.current.temperature2m.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(
                    start = spacing_0_5x,
                    top = spacing_1x
                ),
                text = "°C",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Image(
            alignment = Alignment.CenterEnd,
            painter = painterResource(weather.current.getDrawableResource()),
            contentDescription = weather.current.weatherDescription,
            modifier = Modifier.size(weatherIconsSizeLarge)
        )
    }
}

@Composable
private fun WeatherProperties(weather: Weather?) = weather?.current?.let {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing_1x),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        WeatherValueWithLabelAndIcon(
            icon = Res.drawable.ic_humidity,
            label = stringResource(Res.string.humidity),
            subLabel = "${weather.current.relativeHumidity2m.toInt()}%",
        )
        WeatherValueWithLabelAndIcon(
            icon = Res.drawable.ic_wind,
            label = stringResource(Res.string.wind),
            subLabel = "${weather.current.windSpeed10m.toInt()}Km/h",
        )
        WeatherValueWithLabelAndIcon(
            icon = Res.drawable.ic_feels_like,
            label = stringResource(Res.string.feels_like),
            subLabel = "${weather.current.relativeHumidity2m.toInt()}°",
        )
    }
}

