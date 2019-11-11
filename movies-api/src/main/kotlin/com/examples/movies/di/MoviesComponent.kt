package com.examples.movies.di

import com.examples.core.di.NetworkComponentApi
import dagger.Component
import java.lang.ref.WeakReference

@Component(
    dependencies = [MoviesDependencies::class],
    modules = [MoviesRepositoryModule::class])
@com.examples.core.di.scope.PerFeature
interface MoviesComponent : MoviesComponentApi {

  companion object {
    @Volatile
    private lateinit var componentWeak: WeakReference<MoviesComponent>

    fun get(): MoviesComponent {
      if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
        synchronized(MoviesComponent::class) {
          if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
            val component = DaggerMoviesComponent.builder()
                .moviesDependencies(
                    getDependencies())
                .build()
            componentWeak = WeakReference(component)
          }
        }
      }

      return componentWeak.get() ?: throw IllegalArgumentException("Component not initialized")
    }

    private fun getDependencies(): MoviesDependenciesComponent {
      return DaggerMoviesComponent_MoviesDependenciesComponent
          .builder()
          .networkComponentApi(com.examples.core.di.proxy.ComponentProxy.getComponent())
          .networkHandlerComponentApi(com.examples.core.di.proxy.ComponentProxy.getComponent())
          .build()
    }
  }

  @Component(
      dependencies = [
        NetworkComponentApi::class,
        com.examples.core.di.api.NetworkHandlerComponentApi::class
      ])
  @com.examples.core.di.scope.PerFeature
  interface MoviesDependenciesComponent : MoviesDependencies
}