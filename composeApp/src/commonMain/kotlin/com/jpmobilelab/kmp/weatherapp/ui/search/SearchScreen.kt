package com.jpmobilelab.kmp.weatherapp.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.close_hint
import cmp_weatherapp.composeapp.generated.resources.locations
import cmp_weatherapp.composeapp.generated.resources.search_hint
import coil3.compose.AsyncImage
import com.jpmobilelab.kmp.weatherapp.domain.model.Location
import com.jpmobilelab.kmp.weatherapp.theme.searchItemHeight
import com.jpmobilelab.kmp.weatherapp.theme.spacing_1x
import com.jpmobilelab.kmp.weatherapp.theme.spacing_2x
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradient
import com.jpmobilelab.kmp.weatherapp.theme.verticalGradientStartingColor
import com.jpmobilelab.kmp.weatherapp.ui.composables.TopBarBackButton
import com.jpmobilelab.kmp.weatherapp.ui.composables.TransparentBox
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreenRoot(
    viewModel: SearchViewModel = koinViewModel(),
    onLocationClick: (Location) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchScreen(
        state = state,
        onBackClick = onBackClick,
        onAction = { action ->
            when (action) {
                is SearchScreenAction.OnSearchQueryChange -> viewModel.onAction(action)
                is SearchScreenAction.OnLocationClick -> onLocationClick(action.location)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchScreenState,
    onBackClick: () -> Unit = {},
    onAction: (SearchScreenAction) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = verticalGradientStartingColor,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = { Text(text = stringResource(Res.string.locations)) },
                navigationIcon = {
                    TopBarBackButton(onBackClick)
                }
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
            SearchBox(
                modifier = Modifier.fillMaxWidth(),
                searchQuery = state.searchQuery,
                onSearchQueryChange = {
                    onAction(SearchScreenAction.OnSearchQueryChange(it))
                },
            )
            Spacer(modifier = Modifier.height(spacing_2x))
            LazyColumn {
                items(state.searchResults.size) { index ->
                    LocationResult(
                        location = state.searchResults[index],
                        onClick = { onAction(SearchScreenAction.OnLocationClick(it)) })
                    Spacer(modifier = Modifier.height(spacing_2x))
                }
                item {
                    Spacer(modifier = Modifier.height(spacing_2x))
                }
            }
        }
    }
}

@Composable
fun SearchBox(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        shape = RoundedCornerShape(100),
        placeholder = {
            Text(
                text = stringResource(Res.string.search_hint)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotBlank()
            ) {
                IconButton(
                    onClick = {
                        onSearchQueryChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(Res.string.close_hint),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        modifier = modifier
            .minimumInteractiveComponentSize()
    )
}

@Composable
fun LocationResult(
    location: Location,
    onClick: (Location) -> Unit = {}
) {
    TransparentBox(
        modifier = Modifier
            .fillMaxWidth()
            .height(searchItemHeight)
            .clickable { onClick(location) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(end = spacing_1x)
                    .weight(0.9f),
            ) {
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (location.country.isNotBlank()) {
                    Text(
                        text = location.country,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (location.subName.isNotBlank()) {
                    Text(
                        text = location.subName,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(end = spacing_1x)
                    .weight(0.1f),
            ) {
                AsyncImage(
                    model = location.flagUrl,
                    contentDescription = location.country,
                )
            }
        }

    }
}
