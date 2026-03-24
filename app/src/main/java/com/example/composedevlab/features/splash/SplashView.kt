package com.example.composedevlab.features.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedevlab.ui.theme.ComposeDevLabTheme

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    SplashScreen(state = state)
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
