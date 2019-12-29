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

import com.examples.core.di.proxy.ComponentProxy
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