package online.personaapp.core.di

import dagger.Component
import online.personaapp.core.di.proxy.ComponentProxy
import online.personaapp.core.di.scope.PerView
import online.personaapp.core.presentation.RouteActivity
import online.personaapp.core.presentation.navigation.di.api.NavigationComponentApi

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