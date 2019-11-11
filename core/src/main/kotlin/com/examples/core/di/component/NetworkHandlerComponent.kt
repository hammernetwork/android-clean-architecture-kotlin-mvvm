package com.examples.core.di.component

import dagger.Component
import java.lang.ref.WeakReference
import javax.inject.Singleton


@Component(
    dependencies = [com.examples.core.di.dependencies.NetworkHandlerDependencies::class]
)
@Singleton
interface NetworkHandlerComponent : com.examples.core.di.api.NetworkHandlerComponentApi {

  companion object {
    @Volatile
    private lateinit var componentWeak: WeakReference<com.examples.core.di.component.NetworkHandlerComponent>

    fun get(): com.examples.core.di.component.NetworkHandlerComponent {
      if (!this::componentWeak.isInitialized || com.examples.core.di.component.NetworkHandlerComponent.Companion.componentWeak.get() == null) {
        synchronized(com.examples.core.di.component.NetworkHandlerComponent::class) {
          if (!this::componentWeak.isInitialized || com.examples.core.di.component.NetworkHandlerComponent.Companion.componentWeak.get() == null) {
            val component = DaggerNetworkHandlerComponent
                .builder()
                .networkHandlerDependencies(
                    com.examples.core.di.component.NetworkHandlerComponent.Companion.getDependencies())
                .build()
            com.examples.core.di.component.NetworkHandlerComponent.Companion.componentWeak = WeakReference(
                component)
          }
        }
      }

      return com.examples.core.di.component.NetworkHandlerComponent.Companion.componentWeak.get()
          ?: throw IllegalArgumentException("Component not initialized")
    }

    private fun getDependencies(): com.examples.core.di.component.NetworkHandlerComponent.NetworkHandlerDependenciesComponent {
      return DaggerNetworkHandlerComponent_NetworkHandlerDependenciesComponent
          .builder()
          .applicationContextComponentApi(
              com.examples.core.di.component.ApplicationContextComponent.Companion.get())
          .build()
    }
  }

  @Component(
      dependencies = [
        com.examples.core.di.api.ApplicationContextComponentApi::class
      ])
  @Singleton
  interface NetworkHandlerDependenciesComponent : com.examples.core.di.dependencies.NetworkHandlerDependencies

}