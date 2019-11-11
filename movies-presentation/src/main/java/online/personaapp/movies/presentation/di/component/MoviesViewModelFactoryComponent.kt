package online.personaapp.movies.presentation.di.component

import dagger.Component
import online.personaapp.core.di.proxy.ComponentProxy
import online.personaapp.movies.di.MoviesComponentApi
import online.personaapp.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import online.personaapp.movies.presentation.di.dependencies.MoviesViewModelFactoryDependencies
import online.personaapp.movies.presentation.di.module.ViewModelModule
import java.lang.ref.WeakReference
import javax.inject.Singleton

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
@Component(
    dependencies = [MoviesViewModelFactoryDependencies::class],
    modules = [ViewModelModule::class]
)
@Singleton
interface MoviesViewModelFactoryComponent : MoviesViewModelFactoryComponentApi {

  companion object {
    @Volatile
    private lateinit var componentWeak: WeakReference<MoviesViewModelFactoryComponent>

    fun get(): MoviesViewModelFactoryComponent {
      if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
        synchronized(MoviesViewModelFactoryComponent::class) {
          if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
            val component = DaggerMoviesViewModelFactoryComponent
                .builder()
                .moviesViewModelFactoryDependencies(getDependencies())
                .build()
            componentWeak = WeakReference(component)
          }
        }
      }

      return componentWeak.get() ?: throw IllegalArgumentException("Component not initialized")
    }

    private fun getDependencies(): MoviesViewModelFactoryDependenciesComponent {
      return DaggerMoviesViewModelFactoryComponent_MoviesViewModelFactoryDependenciesComponent
          .builder()
          .moviesComponentApi(ComponentProxy.getComponent())
          .build()
    }
  }

  @Component(
      dependencies = [
        MoviesComponentApi::class
      ])
  @Singleton
  interface MoviesViewModelFactoryDependenciesComponent : MoviesViewModelFactoryDependencies

}