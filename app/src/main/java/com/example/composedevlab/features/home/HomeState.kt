package com.example.composedevlab.features.home

data class HomeState(
    val welcomeMessage: String = "Bem-vindo à Home!",
    val isLoading: Boolean = false
)
