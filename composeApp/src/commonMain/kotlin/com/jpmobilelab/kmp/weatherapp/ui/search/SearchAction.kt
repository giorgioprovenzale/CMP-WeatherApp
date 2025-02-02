package com.jpmobilelab.kmp.weatherapp.ui.search

import com.jpmobilelab.kmp.weatherapp.domain.model.CurrentLocation
import com.jpmobilelab.kmp.weatherapp.domain.model.Location

sealed interface SearchScreenAction {
    data class OnSearchQueryChange(val query: String) : SearchScreenAction
    data class OnLocationClick(val location: Location) : SearchScreenAction
    data class OnCurrentLocationClick(val currentLocation: CurrentLocation) : SearchScreenAction
}