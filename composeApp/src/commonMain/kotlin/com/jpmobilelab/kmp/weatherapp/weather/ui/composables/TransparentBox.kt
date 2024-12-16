package com.jpmobilelab.kmp.weatherapp.weather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jpmobilelab.kmp.weatherapp.theme.spacing_1x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_2x
import com.jpmobilelab.kmp.weatherapp.theme.transparentBoxBackground

@Composable
fun TransparentBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(transparentBoxBackground, RoundedCornerShape(spacing_2x))
            .padding(vertical = spacing_2x, horizontal = spacing_1x)
    ) {
        content()
    }
}