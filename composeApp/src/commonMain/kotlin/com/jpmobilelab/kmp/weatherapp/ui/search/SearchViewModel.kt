@file:OptIn(FlowPreview::class)

package com.jpmobilelab.kmp.weatherapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmobilelab.kmp.weatherapp.domain.core.onSuccess
import com.jpmobilelab.kmp.weatherapp.domain.repository.LocationRepository
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
    private val locationRepository: LocationRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state = MutableStateFlow<SearchScreenState>(SearchScreenState())
    val state = _state
        .onStart {
            observeSearchQuery()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
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

            is SearchScreenAction.OnLocationClick -> {
                println(action.location.toString())
            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {

                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchLocations(query)
                    }
                }
            }
            .launchIn(viewModelScope)
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
