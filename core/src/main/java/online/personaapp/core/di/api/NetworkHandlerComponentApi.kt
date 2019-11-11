package online.personaapp.core.di.api

import online.personaapp.core.platform.NetworkHandler

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
interface NetworkHandlerComponentApi {
  fun networkHandler(): NetworkHandler
}
