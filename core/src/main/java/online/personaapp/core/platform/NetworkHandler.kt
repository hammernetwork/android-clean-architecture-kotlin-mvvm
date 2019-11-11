package online.personaapp.core.platform

import android.content.Context
import online.personaapp.core.extension.isConnected
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
 * Injectable class which returns information about the network connection state.
 */
@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
  val isConnected get() = context.isConnected
}