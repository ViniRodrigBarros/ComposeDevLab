package com.example.composedevlab.features.onboarding

import com.example.composedevlab.R
import com.example.composedevlab.core.data.models.OnboardingPageModel


data class OnboardingState(
    val pages: List<OnboardingPageModel> = listOf(
        OnboardingPageModel(
            title = "Bem-vindo ao DevLab",
            description = "Explore as melhores práticas de desenvolvimento Android com Jetpack Compose.",
            lottieRes = R.raw.onboarding1
        ),
        OnboardingPageModel(
            title = "Arquitetura Moderna",
            description = "Utilizamos MVVM, Hilt e Clean Architecture para criar apps escaláveis.",
            lottieRes = R.raw.onboarding2
        ),
        OnboardingPageModel(
            title = "Pronto para começar?",
            description = "Agora que você conhece a estrutura, vamos colocar a mão na massa!",
            lottieRes = R.raw.onboarding3
        )
    )
)
