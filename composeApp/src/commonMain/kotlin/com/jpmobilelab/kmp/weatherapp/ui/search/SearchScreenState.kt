package com.jpmobilelab.kmp.weatherapp.ui.search

import com.jpmobilelab.kmp.weatherapp.domain.model.Location

data class SearchScreenState(
    val searchQuery: String = "",
    val searchResults: List<Location> = emptyList()
)
