@file:OptIn(FlowPreview::class)

package com.jpmobilelab.kmp.weatherapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmobilelab.kmp.weatherapp.domain.core.onSuccess
import com.jpmobilelab.kmp.weatherapp.domain.model.CurrentLocation
import com.jpmobilelab.kmp.weatherapp.domain.model.Location
import com.jpmobilelab.kmp.weatherapp.domain.repository.LocationRepository
import com.jpmobilelab.kmp.weatherapp.domain.stateholder.StateHolder
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val locationRepository: LocationRepository,
    private val currentLocationStateHolder: StateHolder<CurrentLocation?>
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state
        .onStart {
            observeCurrentLocation()
            observeRecentLocations()
            observeSearchQuery()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    fun onAction(action: SearchScreenAction) {
        when (action) {
            is SearchScreenAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query,
                        searchResults = emptyList()
                    )
                }
            }
            is SearchScreenAction.OnLocationClick -> saveLocation(action.location)
            is SearchScreenAction.OnCurrentLocationClick -> Unit
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> Unit
                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchLocations(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeCurrentLocation() {
        currentLocationStateHolder.state.onEach { currentLocation ->
            _state.update { it.copy(currentLocation = currentLocation) }
        }.launchIn(viewModelScope)
    }

    private fun saveLocation(location: Location) = viewModelScope.launch {
        locationRepository.saveLocation(location)
    }

    private fun observeRecentLocations() {
        locationRepository.observeLocations().onEach { recentLocations ->
            _state.update {
                it.copy(recentLocations = recentLocations)
            }
        }.launchIn(viewModelScope)
    }

    private fun searchLocations(query: String) = viewModelScope.launch {
        locationRepository.searchLocation(query)
            .onSuccess { results ->
                _state.update {
                    it.copy(searchResults = results)
                }
            }
    }
}
