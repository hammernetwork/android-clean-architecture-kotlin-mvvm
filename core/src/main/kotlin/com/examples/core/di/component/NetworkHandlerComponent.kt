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