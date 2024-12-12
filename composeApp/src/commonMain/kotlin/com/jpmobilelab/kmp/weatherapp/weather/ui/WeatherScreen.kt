package com.jpmobilelab.kmp.weatherapp.weather.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jpmobilelab.kmp.weatherapp.weather.domain.model.Weather
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
            CenterAlignedTopAppBar(
                title = { androidx.compose.material3.Text("Weather Information") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            weather?.current?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Temperature: ${weather.current.temperature2m}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Apparent Temperature: ${weather.current.apparentTemperature}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}