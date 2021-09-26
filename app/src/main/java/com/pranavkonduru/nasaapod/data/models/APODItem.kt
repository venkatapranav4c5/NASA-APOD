package com.pranavkonduru.nasaapod.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod_item")
data class APODItem(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)