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
package com.examples.core.di

import com.examples.core.di.proxy.ComponentProxy
import com.examples.core.di.scope.PerView
import com.examples.core.presentation.RouteActivity
import com.examples.presentation.navigation.di.api.NavigationComponentApi
import dagger.Component

/**
 * Route activity component [RouteActivty]
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */

@PerView
@Component(dependencies = [RouteActivityDependencies::class])
interface RouteActivityComponent {

  fun injectView(routeActivity: RouteActivity)

  companion object {
    fun get(): RouteActivityComponent {
      return DaggerRouteActivityComponent.builder()
          .routeActivityDependencies(getDependencies())
          .build()
    }

    private fun getDependencies(): RouteActivityDependenciesComponent {
      return DaggerRouteActivityComponent_RouteActivityDependenciesComponent.builder()
          .navigationComponentApi(ComponentProxy.getComponent())
          .build()
    }
  }

  @PerView
  @Component(
      dependencies = [
        NavigationComponentApi::class
      ]
  )
  interface RouteActivityDependenciesComponent : RouteActivityDependencies

}