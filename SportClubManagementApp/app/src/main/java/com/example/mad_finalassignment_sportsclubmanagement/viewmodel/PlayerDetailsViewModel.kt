package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.database.PlayerDao
import com.example.mad_finalassignment_sportsclubmanagement.model.Player
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class PlayerDetailsViewModel(
    private val dao: PlayerDao,
    private val playerID: Int) : ViewModel() {

    var player by mutableStateOf<Player?>(null)
        private set

    init {
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        player = dao.getByID(playerID)
    }

    fun update(
        player: Player,
        onComplete: () -> Unit) = viewModelScope.launch {
            dao.update(player)
            refresh()
            onComplete()
    }

    fun delete(
        player: Player,
        onComplete: () -> Unit) = viewModelScope.launch {
            dao.delete(player)
            onComplete()
    }
}