package com.pranavkonduru.nasaapod

import android.app.Application
import com.pranavkonduru.nasaapod.di.components.AppComponent
import com.pranavkonduru.nasaapod.di.components.DaggerAppComponent
import com.pranavkonduru.nasaapod.di.modules.AppModule

class NasaNewsApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}