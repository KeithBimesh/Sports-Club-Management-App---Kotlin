package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_finalassignment_sportsclubmanagement.database.MatchDao
import com.example.mad_finalassignment_sportsclubmanagement.model.Match
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MatchViewModel(
    private val dao: MatchDao) : ViewModel() {

    var matches by mutableStateOf(listOf<Match>())
        private set

    init {
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        matches = dao.getAll()
    }

    fun add(match: Match, onComplete: () -> Unit) = viewModelScope.launch {
        dao.insert(match)
        onComplete()
    }

    fun update(match: Match) = viewModelScope.launch {
        dao.update(match)
        refresh()
    }

    fun remove(match: Match) = viewModelScope.launch {
        dao.delete(match)
        refresh()
    }
}