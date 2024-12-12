package com.jpmobilelab.kmp.weatherapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.jpmobilelab.kmp.weatherapp.weather.ui.WeatherScreenRoot
import com.jpmobilelab.kmp.weatherapp.weather.ui.WeatherViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.WeatherGraph
        ) {
            navigation<Route.WeatherGraph>(
                startDestination = Route.WeatherHome
            ) {
                composable<Route.WeatherHome> {
                    val viewModel = koinViewModel<WeatherViewModel>()

                    WeatherScreenRoot(viewModel)
                }
            }
        }
    }
}
