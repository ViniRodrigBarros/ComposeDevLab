package com.example.composedevlab.features.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedevlab.features.NavigationEvent
import com.example.composedevlab.ui.theme.ComposeDevLabTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onNavigate: (NavigationEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.navigationEvent.collectLatest { event ->
            onNavigate(event)
        }
    }

    OnboardingScreen(state = state)
}

@Composable
fun OnboardingScreen(
    state: OnboardingState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text("Onboarding Screen")
        // Conteúdo do Onboarding aqui
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    ComposeDevLabTheme {
        OnboardingScreen(state = OnboardingState())
    }
}
