package com.pranavkonduru.nasaapod.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pranavkonduru.nasaapod.data.models.APODItem
import kotlinx.coroutines.flow.Flow

@Dao
interface APODDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAPODItemToDB(apodItem: APODItem)

    @Query("SELECT * FROM apod_item where date = :date")
    fun getAPODItemFromDB(date: String): Flow<APODItem>

    @Query("DELETE FROM apod_item")
    suspend fun deleteAPODItemFromDB()

    @Query("SELECT EXISTS(SELECT * FROM apod_item)")
    fun hasAPODItem(): Boolean
}