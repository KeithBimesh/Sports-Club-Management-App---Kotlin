package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.Preferences.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PreferencesViewModel(application: Application) : AndroidViewModel(application) {
    private val preferences = UserPreferences(application)

    var darkMode by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            darkMode = preferences.darkMode.first()
        }
    }
    fun ChangeDarkMode(enabled: Boolean) {
        darkMode = enabled

        viewModelScope.launch {
            preferences.saveDarkMode(enabled)
        }
    }
}