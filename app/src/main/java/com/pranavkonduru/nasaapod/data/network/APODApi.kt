package com.pranavkonduru.nasaapod.data.network

import com.pranavkonduru.nasaapod.data.models.APODItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APODApi {
    @GET("apod")
    suspend fun getAstronomyPicOfTheDay(@Query("api_key") apiKey: String): Response<APODItem>
}