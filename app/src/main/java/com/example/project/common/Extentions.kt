package com.example.project.common

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager

fun Context.isOnline(): Boolean {
    return try {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        //should check null because in airplane mode it will be null
        netInfo != null && netInfo.isConnected
    } catch (e: NullPointerException) {
        e.printStackTrace()
        false
    }
}
fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}