package com.jpmobilelab.kmp.weatherapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val verticalGradientColorStops
    @Composable
    get() = if (isSystemInDarkTheme())
        arrayOf(
            0.1f to Color(0xFF391A49),
            0.32f to Color(0xFF301D5C),
            0.56f to Color(0xFF262171),
            0.70f to Color(0xFF301D5C),
            0.91f to Color(0xFF391A49),
        ) else
        arrayOf(
            0.1f to Color(0xFFE8F0FE),
            0.32f to Color(0xFFD8E2FD),
            0.56f to Color(0xFFC8D4FC),
            0.70f to Color(0xFFD8E2FD),
            0.91f to Color(0xFFE8F0FE),
        )

val verticalGradientStartingColor
    @Composable
    get() = verticalGradientColorStops.first().second

val verticalGradient
    @Composable
    get() = Brush.verticalGradient(colorStops = verticalGradientColorStops)

val transparentBoxBackground
    get() = Color(0xFFAAA5A5).copy(alpha = 0.3f)