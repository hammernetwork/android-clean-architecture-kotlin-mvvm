package com.examples.di.factory

import android.app.Application
import com.examples.core.di.NetworkComponent
import com.examples.core.di.NetworkComponentApi
import com.examples.core.presentation.navigation.di.api.NavigationComponentApi
import com.examples.di.component.NavigationComponent
import com.examples.movies.di.MoviesComponent
import com.examples.movies.di.MoviesComponentApi


class ComponentFactoryImpl(application: Application) : com.examples.core.di.proxy.ComponentFactory {

  init {
    com.examples.core.di.component.ApplicationContextComponent.init(application = application)
  }

  /**
   * Factory for creating component
   * For support add you own component cases
   */
  override fun createComponent(clazz: Class<*>): Any {
    return when (clazz) {
      // Application context component, should be initialized on app create
      // Singleton scope
      com.examples.core.di.api.ApplicationContextComponentApi::class.java -> com.examples.core.di.component.ApplicationContextComponent.get()
      NetworkComponentApi::class.java -> NetworkComponent.get()
      NavigationComponentApi::class.java -> NavigationComponent.get()
      com.examples.core.di.api.NetworkHandlerComponentApi::class.java -> com.examples.core.di.component.NetworkHandlerComponent.get()

      //PerFeature scope
      MoviesComponentApi::class.java -> MoviesComponent.get()

      else -> throw IllegalStateException("Unsupported type")
    }
  }
}
