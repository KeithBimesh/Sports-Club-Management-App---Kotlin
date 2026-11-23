package com.example.mad_finalassignment_sportsclubmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mad_finalassignment_sportsclubmanagement.model.Match
import com.example.mad_finalassignment_sportsclubmanagement.model.Player
import com.example.mad_finalassignment_sportsclubmanagement.model.Team

@Database(
    entities = [Team::class, Player::class, Match::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {

    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao
    abstract fun matchDao(): MatchDao

    companion object {
        private var db: AppDB? = null

        fun get(context: Context): AppDB {
            if(db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "app.db"
                ).build()
            }
            return db!!
        }
    }
}