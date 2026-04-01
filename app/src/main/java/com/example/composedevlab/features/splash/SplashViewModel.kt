package com.example.composedevlab.features.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedevlab.core.shared.managers.AppManager
import com.example.composedevlab.features.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appManager: AppManager
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()
    
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    init {
        startSplashTimer()
    }

    private fun startSplashTimer() {
        viewModelScope.launch {
            delay(3000)
            val hasViewedOnboarding = appManager.hasViewedOnboarding()
            
            Log.d("SplashVM", "Iniciando decisão de navegação. Já viu onboarding: $hasViewedOnboarding")
            
            if (hasViewedOnboarding) {
                Log.d("SplashVM", "Navegando para HOME")
                _navigationEvent.emit(NavigationEvent.GoToHome)
                return@launch
            }

            Log.d("SplashVM", "Navegando para ONBOARDING")
            _navigationEvent.emit(NavigationEvent.GotoOnboarding)
        }
    }
}
