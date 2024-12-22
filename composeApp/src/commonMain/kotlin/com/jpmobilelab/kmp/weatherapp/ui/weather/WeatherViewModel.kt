package com.jpmobilelab.kmp.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.current_location
import cmp_weatherapp.composeapp.generated.resources.error_location_not_found
import cmp_weatherapp.composeapp.generated.resources.error_location_permission_denied
import cmp_weatherapp.composeapp.generated.resources.error_location_permission_denied_forever
import cmp_weatherapp.composeapp.generated.resources.error_location_service_not_supported
import com.jpmobilelab.kmp.weatherapp.domain.core.onSuccess
import com.jpmobilelab.kmp.weatherapp.domain.repository.WeatherRepository
import com.jpmobilelab.kmp.weatherapp.ui.core.UiText
import dev.jordond.compass.geocoder.Geocoder
import dev.jordond.compass.geocoder.placeOrNull
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.GeolocatorResult
import dev.jordond.compass.geolocation.mobile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state = _state
        .onStart {
            getLocation()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    private suspend fun fetchWeather(latitude: Float, longitude: Float, locationName: UiText) {
        _state.update { WeatherState.Loading }
        weatherRepository.getCurrentWeatherData(latitude, longitude)
            .onSuccess { weather ->
                _state.update {
                    WeatherState.Content(
                        location = locationName,
                        weather = weather
                    )
                }
            }
    }

    fun getLocation() {
        viewModelScope.launch {
            _state.update { WeatherState.Loading }
            val geolocator: Geolocator = Geolocator.mobile()

            when (val result: GeolocatorResult = geolocator.current()) {
                is GeolocatorResult.Success -> {
                    val geocoder = Geocoder()
                    val location = geocoder.placeOrNull(result.data.coordinates)
                    fetchWeather(
                        latitude = result.data.coordinates.latitude.toFloat(),
                        longitude = result.data.coordinates.longitude.toFloat(),
                        locationName = if (location?.locality != null)
                            UiText.DynamicString(location.locality.orEmpty())
                        else
                            UiText.StringResourceId(
                                Res.string.current_location
                            )
                    )
                }

                is GeolocatorResult.Error -> handleLocationError(result)
            }
        }
    }

    private fun handleLocationError(error: GeolocatorResult.Error) {
        val state = when (error) {
            is GeolocatorResult.NotSupported ->
                WeatherState.Error(
                    message = UiText.StringResourceId(Res.string.error_location_service_not_supported),
                    showButton = true
                )

            is GeolocatorResult.NotFound ->
                WeatherState.Error(
                    message = UiText.StringResourceId(Res.string.error_location_not_found),
                    showButton = true
                )

            is GeolocatorResult.PermissionError ->
                WeatherState.Error(
                    message = UiText.StringResourceId(Res.string.error_location_permission_denied),
                    showButton = true
                )

            is GeolocatorResult.GeolocationFailed ->
                WeatherState.Error(
                    message = UiText.StringResourceId(Res.string.error_location_not_found),
                    showButton = true
                )

            is GeolocatorResult.PermissionDenied -> if (error.forever) {
                WeatherState.Error(
                    message = UiText.StringResourceId(Res.string.error_location_permission_denied_forever),
                    showButton = false
                )
            } else {
                WeatherState.Error(
                    message = UiText.StringResourceId(Res.string.error_location_permission_denied),
                    showButton = true
                )
            }

            else ->
                WeatherState.Error(
                    message = UiText.StringResourceId(Res.string.error_location_not_found),
                    showButton = true
                )

        }
        _state.update { state }
    }
}
