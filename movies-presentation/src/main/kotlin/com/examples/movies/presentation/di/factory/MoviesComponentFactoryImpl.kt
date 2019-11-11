package com.examples.movies.presentation.di.factory

import android.content.Context
import com.examples.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import com.examples.movies.presentation.di.component.MoviesViewModelFactoryComponent

class MoviesComponentFactoryImpl(
    private val context: Context
) : com.examples.core.di.proxy.ComponentFactory {
  override fun createComponent(clazz: Class<*>): Any {
    return when (clazz) {
      MoviesViewModelFactoryComponentApi::class.java -> MoviesViewModelFactoryComponent.get()

      else -> throw IllegalArgumentException("Unsupported type")
    }
  }
}
