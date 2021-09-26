package com.pranavkonduru.nasaapod.di.modules

import android.content.Context
import com.pranavkonduru.nasaapod.NasaNewsApplication
import com.pranavkonduru.nasaapod.data.network.APODApi
import com.pranavkonduru.nasaapod.data.repositories.APODRepository
import com.pranavkonduru.nasaapod.room.dao.APODDao
import com.pranavkonduru.nasaapod.room.database.APODDatabase
import com.pranavkonduru.nasaapod.ui.apod.viewmodel.APODViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: NasaNewsApplication) {
    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    internal fun getAPODDatabase(context: Context): APODDatabase {
        return APODDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    internal fun getAPODDao(database: APODDatabase): APODDao {
        return database.aPODDao()
    }

    @Provides
    @Singleton
    internal fun getAPODRepository(apodApi: APODApi, apodDao: APODDao): APODRepository {
        return APODRepository(apodApi, apodDao)
    }
    @Provides
    @Singleton
    internal fun getAPODViewModelFactory(
        apodRepository: APODRepository
    ):  APODViewModelFactory {
        return APODViewModelFactory(apodRepository)
    }
}