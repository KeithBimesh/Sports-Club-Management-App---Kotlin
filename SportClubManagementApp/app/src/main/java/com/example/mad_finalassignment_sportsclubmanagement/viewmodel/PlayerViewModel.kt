package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.database.PlayerDao
import com.example.mad_finalassignment_sportsclubmanagement.model.Player
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class PlayerViewModel(
    private val dao: PlayerDao,
    private val teamID: Int) : ViewModel() {
    var players by mutableStateOf(listOf<Player>())
        private set


    init{
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        players = dao.getForTeam(teamID)
    }

    fun add(player: Player, onComplete: () -> Unit) = viewModelScope.launch {
        dao.insert(player)
        refresh()
        onComplete()
    }

    fun update(player: Player) = viewModelScope.launch {
        dao.update(player)
        refresh()
    }

    fun remove(player: Player) = viewModelScope.launch {
        dao.delete(player)
        refresh()
    }
}