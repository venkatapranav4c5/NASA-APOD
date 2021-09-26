package com.pranavkonduru.nasaapod.data.repositories

import android.annotation.SuppressLint
import com.pranavkonduru.nasaapod.data.models.APODItem
import com.pranavkonduru.nasaapod.data.network.APODApi
import com.pranavkonduru.nasaapod.data.network.SafeApiRequest
import com.pranavkonduru.nasaapod.room.dao.APODDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*

@Suppress("RedundantSuspendModifier")
class APODRepository(
    private val apodApi: APODApi,
    private val apodDao: APODDao
) : SafeApiRequest() {

    private val getAPODItem: Flow<APODItem> = apodDao.getAPODItemFromDB(getTodayDate())
    private suspend fun hasAPODItem() = apodDao.hasAPODItem()
    suspend fun deleteAndInsertAPOD(apodItem: APODItem): APODItem {
        apodDao.deleteAPODItemFromDB()
        apodDao.insertAPODItemToDB(apodItem)
        return  apodItem
    }
    suspend fun getAstronomyPicOfTheDay(apiKey: String) = apiRequest {
        apodApi.getAstronomyPicOfTheDay(apiKey)
    }
    suspend fun getAPODFromDB(): APODItem?{
        return when(hasAPODItem()) {
            true ->  getAPODItem.first()
            else ->  null
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun getTodayDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }


}