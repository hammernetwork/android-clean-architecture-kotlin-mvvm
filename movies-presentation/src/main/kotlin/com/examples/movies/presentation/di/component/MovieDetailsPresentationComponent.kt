package com.examples.movies.presentation.di.component

import android.content.Context
import com.examples.core.di.scope.PerView
import com.examples.core.presentation.navigation.di.api.NavigationComponentApi
import com.examples.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import com.examples.movies.presentation.di.dependencies.MovieDetailsPresentationDependencies
import com.examples.movies.presentation.view.MovieDetailsFragment
import dagger.Component

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
          .movieDetailsPresentationDependencies(
              getDependencies(
                  context))
          .build()
    }

    private fun getDependencies(context: Context): MovieDetailsPresentationDependenciesComponent {
      return DaggerMovieDetailsPresentationComponent_MovieDetailsPresentationDependenciesComponent.builder()
          .navigationComponentApi(com.examples.core.di.proxy.ComponentProxy.getComponent())
          .moviesViewModelFactoryComponentApi(
              com.examples.core.di.proxy.ActivityComponentProxy.getComponent(context))
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