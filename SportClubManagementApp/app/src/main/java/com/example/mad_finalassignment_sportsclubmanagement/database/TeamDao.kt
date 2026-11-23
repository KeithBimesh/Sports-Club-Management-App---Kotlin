package com.example.mad_finalassignment_sportsclubmanagement.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mad_finalassignment_sportsclubmanagement.model.Team

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    suspend fun getAll(): List<Team>

    @Query("SELECT * FROM teams WHERE id = :teamID")
    suspend fun getByID(teamID: Int): Team?

    @Insert
    suspend fun insert(team: Team)

    @Update
    suspend fun update(team: Team)

    @Delete
    suspend fun delete(team: Team)
}