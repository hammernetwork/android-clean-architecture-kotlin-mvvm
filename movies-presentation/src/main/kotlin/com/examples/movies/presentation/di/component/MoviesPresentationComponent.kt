/**
 * Copyright (C) 2018 Andriy Se Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.examples.movies.presentation.di.component

import android.content.Context
import com.examples.core.di.scope.PerView
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

@PerView
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

  @PerView
  @Component(
      dependencies = [
        NavigationComponentApi::class,
        MoviesViewModelFactoryComponentApi::class
      ]
  )
  interface MoviesPresentationDependenciesComponent : MoviesPresentationDependencies

}