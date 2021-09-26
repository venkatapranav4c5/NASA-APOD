package com.pranavkonduru.nasaapod.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}