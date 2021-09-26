package com.pranavkonduru.nasaapod.di.components

import com.pranavkonduru.nasaapod.di.modules.ApiModule
import com.pranavkonduru.nasaapod.di.modules.AppModule
import com.pranavkonduru.nasaapod.ui.apod.ApodActivity
import com.pranavkonduru.nasaapod.ui.apod.viewmodel.APODViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    fun getAPODViewModelFactory(): APODViewModelFactory
    fun inject(apodActivity: ApodActivity)
}