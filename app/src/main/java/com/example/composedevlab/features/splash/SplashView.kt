package com.example.composedevlab.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composedevlab.R
import com.example.composedevlab.features.NavigationEvent
import com.example.composedevlab.ui.theme.ComposeDevLabTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigate: (NavigationEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(viewModel) {
        viewModel.navigationEvent.collectLatest { event ->
            onNavigate(event)
        }
    }

    SplashScreen(state = state)
}

@Composable
fun SplashScreen(
    state: SplashState
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.boom))
    
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Blue),
        contentAlignment = Alignment.Center,


    ) {
        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ComposeDevLabTheme {
        SplashScreen(state = SplashState(searchText = "Preview Mode"))
    }
}
