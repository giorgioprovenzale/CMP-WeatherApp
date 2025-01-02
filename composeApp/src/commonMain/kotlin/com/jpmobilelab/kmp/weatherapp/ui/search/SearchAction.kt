package com.jpmobilelab.kmp.weatherapp.ui.search

sealed interface SearchScreenAction {
    data class OnSearchQueryChange(val query: String) : SearchScreenAction
}