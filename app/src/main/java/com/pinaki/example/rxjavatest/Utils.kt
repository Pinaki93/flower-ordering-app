package com.pinaki.example.rxjavatest

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by pinaki93 on 05/07/18.
 */
fun isOnline(context: Context): Boolean {
    val conMananger = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var activeNetworkInfo = conMananger.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun getImageUrl(name: String) = BuildConfig.BASE_IMG_URL + name