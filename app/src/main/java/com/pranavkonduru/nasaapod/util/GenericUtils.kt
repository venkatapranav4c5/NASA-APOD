package com.pranavkonduru.nasaapod.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}

@SuppressLint("SimpleDateFormat")
fun String.isToday(): Boolean {
    val format = SimpleDateFormat("yyyy-MM-dd")
    val today = format.format(Date())
    return today == this
}

@SuppressLint("SimpleDateFormat")
fun getDateTextWithFormat(date: String): String{
    val parser = SimpleDateFormat("yyyy-MM-dd")
    val formatter = SimpleDateFormat("yyyy MMMM dd")
    return formatter.format(parser.parse(date)!!)
}