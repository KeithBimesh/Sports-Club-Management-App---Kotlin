package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mad_finalassignment_sportsclubmanagement.database.MatchDao
import com.example.mad_finalassignment_sportsclubmanagement.database.TeamDao
import com.example.mad_finalassignment_sportsclubmanagement.database.PlayerDao


class ViewModelFactory(
    private val teamDao: TeamDao,
    private val playerDao: PlayerDao,
    private val matchDao: MatchDao,
    private val teamID: Int = 0,
    private val playerID: Int = 0,
    private val matchID: Int = 0
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            return TeamViewModel(teamDao) as T
        }

        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            return PlayerViewModel(playerDao, teamID) as T
        }

        if (modelClass.isAssignableFrom(PlayerDetailsViewModel::class.java)) {
            return PlayerDetailsViewModel(playerDao, playerID) as T
        }

        if (modelClass.isAssignableFrom(TeamDetailsViewModel::class.java)) {
            return TeamDetailsViewModel(teamDao, teamID) as T
        }

        if(modelClass.isAssignableFrom(MatchViewModel::class.java)) {
            return MatchViewModel(matchDao) as T
        }

        if(modelClass.isAssignableFrom(MatchDetailsViewModel::class.java)) {
            return MatchDetailsViewModel(matchDao, matchID) as T
        }

        if(modelClass.isAssignableFrom(TeamMatchesViewModel::class.java)) {
            return TeamMatchesViewModel(matchDao, teamID) as T
        }

        if(modelClass.isAssignableFrom(StatisticsViewModel::class.java)) {
            return StatisticsViewModel(teamDao, playerDao, matchDao) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }
}