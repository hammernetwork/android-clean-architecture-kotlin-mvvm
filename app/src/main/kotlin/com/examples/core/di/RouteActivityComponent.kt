package com.examples.core.di

import com.examples.core.di.proxy.ComponentProxy
import com.examples.core.di.scope.PerView
import com.examples.core.presentation.RouteActivity
import com.examples.core.presentation.navigation.di.api.NavigationComponentApi
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