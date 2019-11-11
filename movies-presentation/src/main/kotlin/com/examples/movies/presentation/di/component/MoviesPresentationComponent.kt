package com.examples.movies.presentation.di.component

import android.content.Context
import com.examples.core.presentation.navigation.di.api.NavigationComponentApi
import com.examples.movies.presentation.di.api.MoviesViewModelFactoryComponentApi
import com.examples.movies.presentation.di.dependencies.MoviesPresentationDependencies
import com.examples.movies.presentation.view.MoviesFragment
import dagger.Component

/**
 * Route activity component [RouteActivty]
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@com.examples.core.di.scope.PerView
@Component(
    dependencies = [MoviesPresentationDependencies::class]
)
interface MoviesPresentationComponent {

  fun injectView(moviesFragment: MoviesFragment)

  companion object {
    fun get(context: Context): MoviesPresentationComponent {
      return DaggerMoviesPresentationComponent.builder()
          .moviesPresentationDependencies(
              getDependencies(
                  context))
          .build()
    }

    private fun getDependencies(context: Context): MoviesPresentationDependenciesComponent {
      return DaggerMoviesPresentationComponent_MoviesPresentationDependenciesComponent.builder()
          .navigationComponentApi(com.examples.core.di.proxy.ComponentProxy.getComponent())
          .moviesViewModelFactoryComponentApi(
              com.examples.core.di.proxy.ActivityComponentProxy.getComponent(context))
          .build()
    }
  }

  @com.examples.core.di.scope.PerView
  @Component(
      dependencies = [
        NavigationComponentApi::class,
        MoviesViewModelFactoryComponentApi::class
      ]
  )
  interface MoviesPresentationDependenciesComponent : MoviesPresentationDependencies

}