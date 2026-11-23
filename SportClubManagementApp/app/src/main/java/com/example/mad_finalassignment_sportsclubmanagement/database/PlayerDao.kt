package com.example.mad_finalassignment_sportsclubmanagement.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mad_finalassignment_sportsclubmanagement.model.Player

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players")
    suspend fun getAll(): List<Player>
    @Query("SELECT * FROM players WHERE teamID = :teamID")
    suspend fun getForTeam(teamID: Int): List<Player>

    @Query("SELECT * FROM players WHERE id = :playerID")
    suspend fun getByID(playerID: Int): Player?

    @Insert
    suspend fun insert(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)
}