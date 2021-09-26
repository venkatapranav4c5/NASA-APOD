package com.pranavkonduru.nasaapod.ui.apod.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pranavkonduru.nasaapod.data.repositories.APODRepository

@Suppress("UNCHECKED_CAST")
class APODViewModelFactory(private val repository: APODRepository)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return APODViewModel(repository) as T
    }
}