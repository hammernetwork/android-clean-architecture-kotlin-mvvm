package online.personaapp.core.extension

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
val Context.isConnected: Boolean
  get() {
    var result = false
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        ?: return result
    result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      checkConnecteion(cm)
    } else {
      checkConnectionLegacy(cm)
    }
    return result

  }

@TargetApi(Build.VERSION_CODES.M)
private fun checkConnecteion(cm: ConnectivityManager): Boolean {
  var result = false
  cm.getNetworkCapabilities(cm.activeNetwork)?.run {
    result = when {
      hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
      hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
      hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
      else -> false
    }
  }
  return result
}

/**
 * Deprecated method connectivity manager, work for <23 api
 */
@Suppress("DEPRECATION")
private fun checkConnectionLegacy(cm: ConnectivityManager): Boolean {
  var result = false
  cm.activeNetworkInfo?.run {
    if (type == ConnectivityManager.TYPE_WIFI) {
      result = true
    } else if (type == ConnectivityManager.TYPE_MOBILE) {
      result = true
    }
  }
  return result
}
