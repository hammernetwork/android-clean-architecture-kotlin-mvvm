package online.personaapp.di.factory

import android.app.Application
import online.personaap.core.di.NetworkComponent
import online.personaap.core.di.NetworkComponentApi
import online.personaapp.core.di.api.ApplicationContextComponentApi
import online.personaapp.core.di.api.NetworkHandlerComponentApi
import online.personaapp.core.di.component.ApplicationContextComponent
import online.personaapp.core.di.component.NetworkHandlerComponent
import online.personaapp.core.di.proxy.ComponentFactory
import online.personaapp.core.presentation.navigation.di.api.NavigationComponentApi
import online.personaapp.di.component.NavigationComponent
import online.personaapp.movies.di.MoviesComponent
import online.personaapp.movies.di.MoviesComponentApi

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class ComponentFactoryImpl(application: Application) : ComponentFactory {

  init {
    ApplicationContextComponent.init(application = application)
  }

  /**
   * Factory for creating component
   * For support add you own component cases
   */
  override fun createComponent(clazz: Class<*>): Any {
    return when (clazz) {
      // Application context component, should be initialized on app create
      // Singleton scope
      ApplicationContextComponentApi::class.java -> ApplicationContextComponent.get()
      NetworkComponentApi::class.java -> NetworkComponent.get()
      NavigationComponentApi::class.java -> NavigationComponent.get()
      NetworkHandlerComponentApi::class.java -> NetworkHandlerComponent.get()

      //PerFeature scope
      MoviesComponentApi::class.java -> MoviesComponent.get()

      else -> throw IllegalStateException("Unsupported type")
    }
  }
}
