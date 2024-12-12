package com.jpmobilelab.kmp.weatherapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.jpmobilelab.kmp.weatherapp.weather.ui.WeatherScreenRoot
import com.jpmobilelab.kmp.weatherapp.weather.ui.WeatherViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {

        val viewModel = koinViewModel<WeatherViewModel>()

        WeatherScreenRoot(viewModel)
    }
}