package online.personaapp.core.di.component

import dagger.Component
import online.personaapp.core.di.api.ApplicationContextComponentApi
import online.personaapp.core.di.api.NetworkHandlerComponentApi
import online.personaapp.core.di.dependencies.NetworkHandlerDependencies
import java.lang.ref.WeakReference
import javax.inject.Singleton

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
@Component(
    dependencies = [NetworkHandlerDependencies::class]
)
@Singleton
interface NetworkHandlerComponent : NetworkHandlerComponentApi {

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

      return componentWeak.get() ?: throw IllegalArgumentException("Component not initialized")
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