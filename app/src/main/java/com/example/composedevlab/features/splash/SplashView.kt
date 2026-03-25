package com.example.composedevlab.features.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedevlab.features.NavigationEvent
import com.example.composedevlab.ui.theme.ComposeDevLabTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigate: (NavigationEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()
    SplashScreen(state = state)

    LaunchedEffect(viewModel) {
        viewModel.navigationEvent.collectLatest { event ->
            onNavigate(event)
        }
    }
    

}

@Composable
fun SplashScreen(
    state: SplashState
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Splash Screen - Search: ${state.searchText}")
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ComposeDevLabTheme {
        SplashScreen(state = SplashState(searchText = "Preview Mode"))
    }
}
