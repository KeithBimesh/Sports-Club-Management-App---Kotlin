package com.example.mad_finalassignment_sportsclubmanagement.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mad_finalassignment_sportsclubmanagement.model.Match

@Dao
interface MatchDao {

    @Query("SELECT * FROM matches")
    suspend fun getAll(): List<Match>

    @Query("SELECT * FROM matches WHERE teamID = :teamID")
    suspend fun getForTeam(teamID: Int): List<Match>

    @Query("SELECT * FROM matches WHERE id = :matchID")
    suspend fun getByID(matchID: Int): Match?

    @Insert
    suspend fun insert(match: Match)

    @Update
    suspend fun update(match: Match)

    @Delete
    suspend fun delete(match: Match)
}