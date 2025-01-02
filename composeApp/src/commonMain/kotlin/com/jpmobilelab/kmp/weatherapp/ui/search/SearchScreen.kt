package com.jpmobilelab.kmp.weatherapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jpmobilelab.kmp.weatherapp.theme.spacing_2x
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradient
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradientStartingColor

@Composable
fun SearchScreenRoot(
) {
    SearchScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = verticalGradientStartingColor,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = { }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(verticalGradient)
                .padding(innerPadding)
                .padding(horizontal = spacing_2x)
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                )
        ) {
            Text(text = "Search Screen")
        }
    }
}
