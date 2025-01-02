package com.jpmobilelab.kmp.weatherapp.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.ic_arrow_back_ios_24
import org.jetbrains.compose.resources.vectorResource

@Composable
actual fun TopBarBackButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = vectorResource(
                Res.drawable.ic_arrow_back_ios_24
            ),
            contentDescription = "back",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}