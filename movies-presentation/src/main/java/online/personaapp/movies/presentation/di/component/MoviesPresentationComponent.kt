package online.personaapp.movies.presentation.di.component

import android.content.Context
import dagger.Component
import online.personaapp.core.di.proxy.ActivityComponentProxy
import online.personaapp.core.di.proxy.ComponentProxy
import online.personaapp.core.di.scope.PerView
import online.personaapp.core.presentation.navigation.di.api.NavigationComponentApi
import online.personaapp.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import online.personaapp.movies.presentation.di.dependencies.MoviesPresentationDependencies
import online.personaapp.movies.presentation.view.MoviesFragment

/**
 * Route activity component [RouteActivty]
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@PerView
@Component(
    dependencies = [MoviesPresentationDependencies::class]
)
interface MoviesPresentationComponent {

  fun injectView(moviesFragment: MoviesFragment)

  companion object {
    fun get(context: Context): MoviesPresentationComponent {
      return DaggerMoviesPresentationComponent.builder()
          .moviesPresentationDependencies(getDependencies(context))
          .build()
    }

    private fun getDependencies(context: Context): MoviesPresentationDependenciesComponent {
      return DaggerMoviesPresentationComponent_MoviesPresentationDependenciesComponent.builder()
          .navigationComponentApi(ComponentProxy.getComponent())
          .moviesViewModelFactoryComponentApi(ActivityComponentProxy.getComponent(context))
          .build()
    }
  }

  @PerView
  @Component(
      dependencies = [
        NavigationComponentApi::class,
        MoviesViewModelFactoryComponentApi::class
      ]
  )
  interface MoviesPresentationDependenciesComponent : MoviesPresentationDependencies

}