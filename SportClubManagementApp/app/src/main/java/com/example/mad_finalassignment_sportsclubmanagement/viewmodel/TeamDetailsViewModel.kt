package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.database.TeamDao
import com.example.mad_finalassignment_sportsclubmanagement.model.Team
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.mad_finalassignment_sportsclubmanagement.model.Match

class TeamDetailsViewModel(
    private val dao: TeamDao,
    private val teamID: Int) : ViewModel() {

    var team by mutableStateOf<Team?>(null)
        private set

    init {
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        team = dao.getByID(teamID)
    }
    fun update(team: Team, onComplete: () -> Unit) = viewModelScope.launch {
        dao.update(team)
        refresh()
        onComplete()
    }

    fun delete(team: Team, onComplete: () -> Unit) = viewModelScope.launch {
        dao.delete(team)
        onComplete()
    }
}
