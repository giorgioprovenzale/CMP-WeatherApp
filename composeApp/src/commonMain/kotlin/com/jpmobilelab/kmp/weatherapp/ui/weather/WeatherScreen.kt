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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.feels_like
import cmp_weatherapp.composeapp.generated.resources.get_location
import cmp_weatherapp.composeapp.generated.resources.humidity
import cmp_weatherapp.composeapp.generated.resources.ic_feels_like
import cmp_weatherapp.composeapp.generated.resources.ic_humidity
import cmp_weatherapp.composeapp.generated.resources.ic_rain
import cmp_weatherapp.composeapp.generated.resources.ic_wind
import cmp_weatherapp.composeapp.generated.resources.last_update
import cmp_weatherapp.composeapp.generated.resources.next_24_hours
import cmp_weatherapp.composeapp.generated.resources.wind
import com.jpmobilelab.kmp.weatherapp.domain.model.HourlyWeather
import com.jpmobilelab.kmp.weatherapp.domain.model.Weather
import com.jpmobilelab.kmp.weatherapp.theme.spacing_0_5x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_1x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_2x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_3x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_6x
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradient
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradientStartingColor
import com.jpmobilelab.kmp.weatherapp.theme.weatherHourlyIconSize
import com.jpmobilelab.kmp.weatherapp.theme.weatherHourlyIconSizeSmall
import com.jpmobilelab.kmp.weatherapp.theme.weatherIconsSizeLarge
import com.jpmobilelab.kmp.weatherapp.ui.composables.TransparentBox
import com.jpmobilelab.kmp.weatherapp.ui.composables.WeatherValueWithLabelAndIcon
import com.jpmobilelab.kmp.weatherapp.ui.core.UiText
import com.jpmobilelab.kmp.weatherapp.ui.formatTimeDifference
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WeatherScreenRoot(
    viewModel: WeatherViewModel = koinViewModel(),
    onSearchClick: () -> Unit,
    placeParams: PlaceParams? = null,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(placeParams) {
        if (placeParams != null) {
            viewModel.fetchWeather(
                latitude = placeParams.latitude,
                longitude = placeParams.longitude,
                locationName = UiText.DynamicString(placeParams.name)
            )
        } else viewModel.getLocation()
    }

    WeatherScreen(
        state = state,
        onGetLocationClick = viewModel::getLocation,
        onSearchClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    state: WeatherState,
    onGetLocationClick: () -> Unit,
    onSearchClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = verticalGradientStartingColor,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = { },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
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
                )
        ) {
            when (state) {
                WeatherState.Loading -> {
                    WeatherLoading()
                }

                is WeatherState.Content -> {
                    Text(
                        text = state.location.asString(),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    WeatherContent(state.weather)
                }

                is WeatherState.Error -> {
                    WeatherError(state, onGetLocationClick)
                }
            }
        }
    }
}

@Composable
private fun WeatherError(state: WeatherState.Error, onGetLocationClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = spacing_2x),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = state.message.asString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        if (state.showButton) {
            Button(
                modifier = Modifier.padding(top = spacing_2x),
                onClick = onGetLocationClick
            ) {
                Text(
                    text = stringResource(Res.string.get_location),
                )
            }
        }
    }
}

@Composable
private fun WeatherLoading() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = spacing_2x),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun WeatherContent(weather: Weather) {
    Column {
        WeatherMainProperties(weather)

        Spacer(Modifier.height(spacing_6x))

        WeatherProperties(weather)

        Spacer(Modifier.height(spacing_6x))

        HourlyWeather(weather.hourly)
    }
}

@Composable
private fun WeatherMainProperties(weather: Weather?) = weather?.current?.let {
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
                text = formatTimeDifference(weather.current.time, TimeZone.of(weather.timezone)),
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
            subLabel = "${weather.current.windSpeed10m.toInt()} Km/h",
        )
        WeatherValueWithLabelAndIcon(
            icon = Res.drawable.ic_feels_like,
            label = stringResource(Res.string.feels_like),
            subLabel = "${weather.current.apparentTemperature.toInt()}°",
        )
    }
}

@Composable
private fun HourlyWeather(hourlyWeather: List<HourlyWeather>) {
    if (hourlyWeather.isEmpty()) return

    TransparentBox(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = spacing_1x),
                text = stringResource(Res.string.next_24_hours),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(
                modifier = Modifier.height(spacing_1x)
            )
            LazyRow {
                items(hourlyWeather.size) {
                    Spacer(
                        modifier = Modifier.padding(start = spacing_1x)
                    )
                    Column(
                        modifier = Modifier.padding(end = spacing_2x),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${hourlyWeather[it].time.hour.toString().padStart(2, '0')}:00",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Image(
                            painter = painterResource(hourlyWeather[it].getDrawableResource()),
                            contentDescription = hourlyWeather[it].time.hour.toString()
                        )
                        Row(modifier = Modifier.padding(start = spacing_1x)) {
                            Text(
                                text = hourlyWeather[it].temperature2m.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                modifier = Modifier.padding(
                                    start = spacing_0_5x,
                                    top = spacing_0_5x,
                                ),
                                text = "°C",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row {
                            Image(
                                painter = painterResource(Res.drawable.ic_rain),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                                modifier = Modifier.size(weatherHourlyIconSize)
                            )
                            Text(
                                modifier = Modifier.padding(top = spacing_0_5x),
                                text = "${hourlyWeather[it].precipitationProbability}%",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(modifier = Modifier.padding(top = spacing_0_5x)) {
                            Image(
                                modifier = Modifier.size(weatherHourlyIconSizeSmall).padding(top = spacing_0_5x),
                                painter = painterResource(Res.drawable.ic_wind),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                            )
                            Text(
                                text = "${hourlyWeather[it].windSpeed10m} Km/h",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                }
            }
        }
    }

}

