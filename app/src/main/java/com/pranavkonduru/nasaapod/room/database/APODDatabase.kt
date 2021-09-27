package com.pranavkonduru.nasaapod.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pranavkonduru.nasaapod.data.models.APODItem
import com.pranavkonduru.nasaapod.room.dao.APODDao

@Database(entities = [APODItem::class], version = 2, exportSchema= false)
abstract class APODDatabase: RoomDatabase() {
    abstract fun aPODDao(): APODDao
    companion object {
        @Volatile
        private var INSTANCE: APODDatabase? = null
        fun getInstance(context: Context): APODDatabase {
            return INSTANCE ?: synchronized(APODDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    APODDatabase::class.java, "apod_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}