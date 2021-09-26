package com.pranavkonduru.nasaapod.di.modules

import android.content.Context
import com.pranavkonduru.nasaapod.data.network.APODApi
import com.pranavkonduru.nasaapod.data.network.NetworkConnectionInterceptor
import com.pranavkonduru.nasaapod.di.qualifiers.APIKey
import com.pranavkonduru.nasaapod.di.qualifiers.EndPoint
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesGetUsersApi(retrofit: Retrofit): APODApi {
        return retrofit.create(APODApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @EndPoint endPoint: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(endPoint)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpInterceptor(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @EndPoint
    internal fun provideBaseURL(): String {
        return "https://api.nasa.gov/planetary/"
    }

    @Provides
    @APIKey
    internal fun provideAPIKey(): String {
        return "acuxUz0oKkGyWy1SpLbMaApbMBhvcjFHtXISpgLY"
    }
}