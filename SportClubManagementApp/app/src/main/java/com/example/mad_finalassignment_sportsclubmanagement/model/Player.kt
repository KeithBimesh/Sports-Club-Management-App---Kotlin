package com.example.mad_finalassignment_sportsclubmanagement.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    val position: String,
    val teamID: Int
)