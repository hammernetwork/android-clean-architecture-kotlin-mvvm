package online.personaapp.movies.di

import dagger.Component
import online.personaap.core.di.NetworkComponentApi
import online.personaapp.core.di.api.NetworkHandlerComponentApi
import online.personaapp.core.di.proxy.ComponentProxy
import online.personaapp.core.di.scope.PerFeature
import java.lang.ref.WeakReference

@Component(
    dependencies = [MoviesDependencies::class],
    modules = [MoviesRepositoryModule::class])
@PerFeature
interface MoviesComponent : MoviesComponentApi {

  companion object {
    @Volatile
    private lateinit var componentWeak: WeakReference<MoviesComponent>

    fun get(): MoviesComponent {
      if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
        synchronized(MoviesComponent::class) {
          if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
            val component = DaggerMoviesComponent.builder()
                .moviesDependencies(getDependencies())
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
          .networkComponentApi(ComponentProxy.getComponent())
          .networkHandlerComponentApi(ComponentProxy.getComponent())
          .build()
    }
  }

  @Component(
      dependencies = [
        NetworkComponentApi::class,
        NetworkHandlerComponentApi::class
      ])
  @PerFeature
  interface MoviesDependenciesComponent : MoviesDependencies
}