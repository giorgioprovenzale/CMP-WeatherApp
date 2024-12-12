package com.jpmobilelab.kmp.weatherapp

import androidx.compose.ui.window.ComposeUIViewController
import com.jpmobilelab.kmp.weatherapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }