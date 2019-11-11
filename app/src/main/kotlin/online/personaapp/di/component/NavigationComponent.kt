package online.personaapp.di.component

import dagger.Component
import online.personaapp.core.presentation.navigation.di.api.NavigationComponentApi
import online.personaapp.di.module.NavigationModule
import javax.inject.Singleton

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
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