package com.pranavkonduru.nasaapod.util

import com.pranavkonduru.nasaapod.data.models.APODItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {
    fun ioThenMain(
        apiWork: suspend (() -> APODItem?),
        dBWork: suspend ((APODItem?) -> APODItem?),
        getFromDB: suspend (() -> APODItem?),
        callback: ((APODItem?) -> Unit),
        errorCallback: ((message: String?) -> Unit)
    ) =
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val data = CoroutineScope(Dispatchers.IO).async rt@{
                    return@rt dBWork(apiWork())
                }.await()
                callback(data)
            } catch (e: ApiException) {
                errorCallback(e.message)
                callback(getDataFromDB(getFromDB))
            } catch (e: NoInternetException) {
                val dbData = getDataFromDB(getFromDB)
                if (dbData != null && !dbData.date.isToday())
                    errorCallback(e.message)
                callback(dbData)
            }
        }

    private suspend fun getDataFromDB(getFromDB: suspend () -> APODItem?) =
        CoroutineScope(Dispatchers.IO).async rt@{
            return@rt getFromDB()
        }.await()
}