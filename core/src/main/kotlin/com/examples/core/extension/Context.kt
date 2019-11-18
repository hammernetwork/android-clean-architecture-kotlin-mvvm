/**
 * Copyright (C) 2018 Andriy Se Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.examples.core.extension

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

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
