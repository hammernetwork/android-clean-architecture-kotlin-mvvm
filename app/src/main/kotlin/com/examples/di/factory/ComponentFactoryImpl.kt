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
package com.examples.di.factory

import android.app.Application
import com.examples.core.di.NetworkComponent
import com.examples.core.di.NetworkComponentApi
import com.examples.core.di.api.ApplicationContextComponentApi
import com.examples.core.di.component.ApplicationContextComponent
import com.examples.core.di.component.NetworkHandlerComponent
import com.examples.core.di.proxy.ComponentFactory
import com.examples.core.presentation.navigation.di.api.NavigationComponentApi
import com.examples.di.component.NavigationComponent
import com.examples.movies.di.MoviesComponent
import com.examples.movies.di.MoviesComponentApi


class ComponentFactoryImpl(application: Application) : ComponentFactory {

  init {
    ApplicationContextComponent.init(application = application)
  }

  /**
   * Factory for creating component
   * For support add you own component cases
   */
  override fun createComponent(clazz: Class<*>): Any {
    return when (clazz) {
      // Application context component, should be initialized on app create
      // Singleton scope
      ApplicationContextComponentApi::class.java -> ApplicationContextComponent.get()
      NetworkComponentApi::class.java -> NetworkComponent.get()
      NavigationComponentApi::class.java -> NavigationComponent.get()
      com.examples.core.di.api.NetworkHandlerComponentApi::class.java -> NetworkHandlerComponent.get()

      //PerFeature scope
      MoviesComponentApi::class.java -> MoviesComponent.get()

      else -> throw IllegalStateException("Unsupported type")
    }
  }
}
