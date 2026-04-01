package com.example.composedevlab.core.data.models

import androidx.annotation.RawRes

data class OnboardingPageModel(
    val title: String,
    val description: String,
    @RawRes val lottieRes: Int
)
