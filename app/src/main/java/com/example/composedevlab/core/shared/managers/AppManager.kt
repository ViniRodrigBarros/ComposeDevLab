package com.example.composedevlab.core.shared.managers

import com.example.composedevlab.core.AppConstants
import com.example.composedevlab.core.data.enumns.AppTheme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppManager(private val storageManager: StorageManager) {

    private val _theme = MutableStateFlow(
        try {
            AppTheme.valueOf(storageManager.getString(AppConstants.KEY_APP_THEME) ?: AppTheme.SYSTEM.name)
        } catch (e: Exception) {
            AppTheme.SYSTEM
        }
    )
    val theme = _theme.asStateFlow()

    fun hasViewedOnboarding(): Boolean {
        return storageManager.getBoolean(AppConstants.KEY_USER_HAS_VIEWED_ONBOARDING)
    }

    fun setViewedOnboarding(viewed: Boolean) {
        storageManager.setBoolean(AppConstants.KEY_USER_HAS_VIEWED_ONBOARDING, viewed)
    }

    fun setTheme(newTheme: AppTheme) {
        storageManager.setString(AppConstants.KEY_APP_THEME, newTheme.name)
        _theme.value = newTheme
    }

    fun getTheme(): AppTheme = _theme.value
}
