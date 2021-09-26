package com.pranavkonduru.nasaapod.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.pranavkonduru.nasaapod.util.NoInternetException
import com.pranavkonduru.nasaapod.util.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(applicationContext)) {
            throw NoInternetException("We are not connected to the internet, showing you the last image we have.")
        } else {
            return chain.proceed(chain.request())
        }
    }
}