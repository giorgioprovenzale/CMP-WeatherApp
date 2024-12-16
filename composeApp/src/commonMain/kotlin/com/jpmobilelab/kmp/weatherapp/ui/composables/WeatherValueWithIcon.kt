package com.jpmobilelab.kmp.weatherapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.jpmobilelab.kmp.weatherapp.theme.spacing_0_5x
import com.jpmobilelab.kmp.weatherapp.theme.weatherPropertyIconSize
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WeatherValueWithLabelAndIcon(
    icon: DrawableResource,
    label: String,
    subLabel: String,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Image(
        painter = painterResource(icon),
        contentDescription = label,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.size(weatherPropertyIconSize)
    )
    Text(
        modifier = Modifier.padding(top = spacing_0_5x),
        text = label.uppercase(),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        modifier = Modifier.padding(top = spacing_0_5x),
        text = subLabel,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}