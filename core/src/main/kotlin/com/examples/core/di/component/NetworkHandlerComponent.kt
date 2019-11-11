package com.examples.core.di.component

import com.examples.core.di.api.ApplicationContextComponentApi
import com.examples.core.di.dependencies.NetworkHandlerDependencies
import dagger.Component
import java.lang.ref.WeakReference
import javax.inject.Singleton

@Component(
    dependencies = [NetworkHandlerDependencies::class]
)
@Singleton
interface NetworkHandlerComponent : com.examples.core.di.api.NetworkHandlerComponentApi {

  companion object {
    @Volatile
    private lateinit var componentWeak: WeakReference<NetworkHandlerComponent>

    fun get(): NetworkHandlerComponent {
      if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
        synchronized(NetworkHandlerComponent::class) {
          if (!this::componentWeak.isInitialized || componentWeak.get() == null) {
            val component = DaggerNetworkHandlerComponent
                .builder()
                .networkHandlerDependencies(getDependencies())
                .build()
            componentWeak = WeakReference(component)
          }
        }
      }

      return componentWeak.get()
          ?: throw IllegalArgumentException("Component not initialized")
    }

    private fun getDependencies(): NetworkHandlerDependenciesComponent {
      return DaggerNetworkHandlerComponent_NetworkHandlerDependenciesComponent
          .builder()
          .applicationContextComponentApi(ApplicationContextComponent.get())
          .build()
    }
  }

  @Component(
      dependencies = [
        ApplicationContextComponentApi::class
      ])
  @Singleton
  interface NetworkHandlerDependenciesComponent : NetworkHandlerDependencies

}