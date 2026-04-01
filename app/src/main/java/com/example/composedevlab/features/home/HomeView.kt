package com.example.composedevlab.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedevlab.features.NavigationEvent
import com.example.composedevlab.ui.theme.ComposeDevLabTheme

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (NavigationEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(state: HomeState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = state.welcomeMessage,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ComposeDevLabTheme {
        HomeScreen(state = HomeState())
    }
}
