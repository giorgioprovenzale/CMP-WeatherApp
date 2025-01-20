package com.jpmobilelab.kmp.weatherapp.domain.stateholder

import kotlinx.coroutines.flow.StateFlow

interface StateHolder<T> {

    val state: StateFlow<T>

    fun update(newState: T)

    fun clear()
}