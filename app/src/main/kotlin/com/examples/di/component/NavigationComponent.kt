package com.examples.di.component

import com.examples.core.presentation.navigation.di.api.NavigationComponentApi
import com.examples.di.module.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class])
interface NavigationComponent : NavigationComponentApi {
  companion object {
    /**
     * Method for create component
     */
    fun get(): NavigationComponent {
      return DaggerNavigationComponent
          .builder()
          .build()
    }

  }

}