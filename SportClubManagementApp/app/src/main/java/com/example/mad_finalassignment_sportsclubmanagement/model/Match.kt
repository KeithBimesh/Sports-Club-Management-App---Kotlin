package com.example.mad_finalassignment_sportsclubmanagement.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Match(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val opponent: String,
    val date: String,
    val location: String,
    val teamID: Int
)