package com.example.composedevlab.features

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedevlab.features.home.HomeRoute
import com.example.composedevlab.features.splash.SplashRoute

sealed class NavigationEvent {
    object GoToHome : NavigationEvent()
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val onNavigate: (NavigationEvent) -> Unit = { event ->
        when (event) {
            NavigationEvent.GoToHome -> {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashRoute(onNavigate = onNavigate)
        }
        
        composable(Screen.Home.route) {
            HomeRoute(onNavigate = onNavigate)
        }
    }
}
