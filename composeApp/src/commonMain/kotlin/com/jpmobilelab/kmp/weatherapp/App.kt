package com.jpmobilelab.kmp.weatherapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jpmobilelab.kmp.weatherapp.theme.DarkColorPalette
import com.jpmobilelab.kmp.weatherapp.theme.LightColorPalette
import com.jpmobilelab.kmp.weatherapp.ui.search.SearchScreenRoot
import com.jpmobilelab.kmp.weatherapp.ui.weather.PlaceParams
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
                startDestination = Route.WeatherHome()
            ) {
                composable<Route.WeatherHome> {
                    WeatherScreenRoot(
                        placeParams = it.toRoute<Route.WeatherHome>().toPlaceParams(),
                        onSearchClick = { navController.navigate(Route.SearchRoute) })
                }

                composable<Route.SearchRoute> {
                    SearchScreenRoot(
                        onLocationClick = { location ->
                            navController.navigate(
                                Route.WeatherHome(
                                    latitude = location.latitude,
                                    longitude = location.longitude,
                                    name = location.name
                                )
                            ) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        onBackClick = navController::popBackStack
                    )
                }
            }
        }
    }
}

private fun Route.WeatherHome.toPlaceParams(): PlaceParams? =
    if (latitude != null && longitude != null && name != null) {
        PlaceParams(latitude, longitude, name)
    } else null

