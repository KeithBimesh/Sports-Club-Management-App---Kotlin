package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.database.TeamDao
import com.example.mad_finalassignment_sportsclubmanagement.database.PlayerDao
import com.example.mad_finalassignment_sportsclubmanagement.database.MatchDao
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val teamDao: TeamDao,
    private val playerDao: PlayerDao,
    private val matchDao: MatchDao
) : ViewModel() {
    var AllTeams by mutableStateOf(0)
        private set
    var AllPlayers by mutableStateOf(0)
        private set
    var AllMatches by mutableStateOf(0)
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            AllTeams = teamDao.getAll().size
            AllPlayers = playerDao.getAll().size
            AllMatches = matchDao.getAll().size
        }
    }
}