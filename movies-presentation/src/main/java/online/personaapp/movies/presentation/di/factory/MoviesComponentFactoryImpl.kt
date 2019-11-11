package online.personaapp.movies.presentation.di.factory

import android.content.Context
import online.personaapp.core.di.proxy.ComponentFactory
import online.personaapp.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import online.personaapp.movies.presentation.di.component.MoviesViewModelFactoryComponent

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class MoviesComponentFactoryImpl(
    private val context: Context
) : ComponentFactory {
  override fun createComponent(clazz: Class<*>): Any {
    return when (clazz) {
      MoviesViewModelFactoryComponentApi::class.java -> MoviesViewModelFactoryComponent.get()

      else -> throw IllegalArgumentException("Unsupported type")
    }
  }
}
