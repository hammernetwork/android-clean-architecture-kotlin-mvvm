package com.examples.movies.presentation.di.component

import com.examples.movies.di.MoviesComponentApi
import com.examples.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import com.examples.movies.presentation.di.dependencies.MoviesViewModelFactoryDependencies
import com.examples.movies.presentation.di.module.ViewModelModule
import dagger.Component
import java.lang.ref.WeakReference
import javax.inject.Singleton

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
        synchronized(
            MoviesViewModelFactoryComponent::class) {
          if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
            val component = DaggerMoviesViewModelFactoryComponent
                .builder()
                .moviesViewModelFactoryDependencies(
                    getDependencies())
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
          .moviesComponentApi(com.examples.core.di.proxy.ComponentProxy.getComponent())
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