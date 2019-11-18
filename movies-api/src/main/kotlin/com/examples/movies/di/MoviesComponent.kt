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
package com.examples.movies.di

import com.examples.core.di.NetworkComponentApi
import com.examples.core.di.api.NetworkHandlerComponentApi
import com.examples.core.di.scope.PerFeature
import dagger.Component
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
        NetworkHandlerComponentApi::class
      ])
  @PerFeature
  interface MoviesDependenciesComponent : MoviesDependencies
}