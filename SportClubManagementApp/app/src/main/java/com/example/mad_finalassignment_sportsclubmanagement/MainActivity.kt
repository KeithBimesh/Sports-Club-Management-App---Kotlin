package com.example.mad_finalassignment_sportsclubmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mad_finalassignment_sportsclubmanagement.navigation.AppNavHost
import com.example.mad_finalassignment_sportsclubmanagement.ui.theme.MAD_FinalAssignment_SportsClubManagementTheme
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.PreferencesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val preferencesViewModel: PreferencesViewModel = viewModel()

            MAD_FinalAssignment_SportsClubManagementTheme(
                darkTheme = preferencesViewModel.darkMode
            ) {
                AppNavHost()
            }
        }
    }
}
