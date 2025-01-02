package com.jpmobilelab.kmp.weatherapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.jpmobilelab.kmp.weatherapp.theme.DarkColorPalette
import com.jpmobilelab.kmp.weatherapp.theme.LightColorPalette
import com.jpmobilelab.kmp.weatherapp.ui.search.SearchScreenRoot
import com.jpmobilelab.kmp.weatherapp.ui.weather.WeatherScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(

        colorScheme = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.WeatherGraph
        ) {
            navigation<Route.WeatherGraph>(
                startDestination = Route.WeatherHome
            ) {
                composable<Route.WeatherHome> {
                    WeatherScreenRoot(
                        onSearchClick = { navController.navigate(Route.SearchRoute) })
                }

                composable<Route.SearchRoute> {
                    SearchScreenRoot(
                        onBackClick = navController::popBackStack
                    )
                }
            }
        }
    }
}
