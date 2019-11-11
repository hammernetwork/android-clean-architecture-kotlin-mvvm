package online.personaapp.movies.presentation.di.component

import android.content.Context
import dagger.Component
import online.personaapp.core.di.proxy.ActivityComponentProxy
import online.personaapp.core.di.proxy.ComponentProxy
import online.personaapp.core.di.scope.PerView
import online.personaapp.core.presentation.navigation.di.api.NavigationComponentApi
import online.personaapp.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import online.personaapp.movies.presentation.di.dependencies.MovieDetailsPresentationDependencies
import online.personaapp.movies.presentation.view.MovieDetailsFragment

/**
 * Route activity component [RouteActivty]
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@PerView
@Component(
    dependencies = [MovieDetailsPresentationDependencies::class]
)
interface MovieDetailsPresentationComponent {

  fun injectView(MoviesDetailsFragment: MovieDetailsFragment)

  companion object {
    fun get(context: Context): MovieDetailsPresentationComponent {
      return DaggerMovieDetailsPresentationComponent.builder()
          .movieDetailsPresentationDependencies(getDependencies(context))
          .build()
    }

    private fun getDependencies(context: Context): MovieDetailsPresentationDependenciesComponent {
      return DaggerMovieDetailsPresentationComponent_MovieDetailsPresentationDependenciesComponent.builder()
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
  interface MovieDetailsPresentationDependenciesComponent : MovieDetailsPresentationDependencies

}