package com.pranavkonduru.nasaapod.ui.apod.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pranavkonduru.nasaapod.data.models.APODItem
import com.pranavkonduru.nasaapod.data.repositories.APODRepository
import com.pranavkonduru.nasaapod.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class APODViewModel(private val repository: APODRepository) : ViewModel() {
    private val _apodItem = MutableLiveData<APODItem>()
    private lateinit var job: Job
    val apodItem: LiveData<APODItem>
        get() = _apodItem
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getAstronomyPicOfTheDay(apiKey: String){
        job = Coroutines.ioThenMain(
            { repository.getAstronomyPicOfTheDay(apiKey) },
            { repository.deleteAndInsertAPOD(it!!) },
            { repository.getAPODFromDB() },
            { _apodItem.value = it },
            { _error.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}