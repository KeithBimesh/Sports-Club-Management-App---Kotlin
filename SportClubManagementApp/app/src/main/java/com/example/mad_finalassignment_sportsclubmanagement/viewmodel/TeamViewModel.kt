package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.model.Team
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.mad_finalassignment_sportsclubmanagement.database.TeamDao

class TeamViewModel(private val dao: TeamDao) : ViewModel() {

    var teams by mutableStateOf(listOf<Team>())
        private set

    init {
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        teams = dao.getAll()
    }

    fun add(team: Team) = viewModelScope.launch {
        dao.insert(team)
        refresh()
    }

    fun update(team: Team) = viewModelScope.launch {
        dao.update(team)
        refresh()
    }

    fun remove(team: Team) = viewModelScope.launch {
        dao.delete(team)
        refresh()
    }
}