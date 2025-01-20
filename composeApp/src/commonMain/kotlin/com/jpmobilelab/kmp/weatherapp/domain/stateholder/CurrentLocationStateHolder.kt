package com.jpmobilelab.kmp.weatherapp.domain.stateholder

import com.jpmobilelab.kmp.weatherapp.domain.model.CurrentLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CurrentLocationStateHolder() : StateHolder<CurrentLocation?> {

    private val _state = MutableStateFlow<CurrentLocation?>(null)
    override val state: StateFlow<CurrentLocation?> = _state

    override fun clear() {
        _state.update { null }
    }

    override fun update(newState: CurrentLocation?) {
        _state.update { newState }
    }
}