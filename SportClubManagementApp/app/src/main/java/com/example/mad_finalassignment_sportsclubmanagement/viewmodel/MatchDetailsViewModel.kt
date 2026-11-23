package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.database.MatchDao
import com.example.mad_finalassignment_sportsclubmanagement.model.Match
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MatchDetailsViewModel(
    private val dao: MatchDao,
    private val matchID: Int) : ViewModel() {

    var match by mutableStateOf<Match?>(null)
        private set

    init {
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        match = dao.getByID(matchID)
    }

    fun update(match: Match, onComplete: () -> Unit) = viewModelScope.launch {
        dao.update(match)
        refresh()
        onComplete()
    }

    fun delete(match: Match, onComplete: () -> Unit) = viewModelScope.launch {
        dao.delete(match)
        onComplete()
    }
}