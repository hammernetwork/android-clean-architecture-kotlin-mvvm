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

import android.app.Application
import com.examples.core.di.api.ApplicationContextComponentApi
import com.examples.core.di.module.ApplicationContextModule
import dagger.Component
import javax.inject.Singleton

/**
 * Component for application context
 */
@Singleton
@Component(modules = [ApplicationContextModule::class])
interface ApplicationContextComponent : ApplicationContextComponentApi {

  companion object {
    @Volatile
    private lateinit var appContextComponent: ApplicationContextComponent

    /**
     * Method for initialize component
     */
    fun init(application: Application) {
      check(!this::appContextComponent.isInitialized) {
        "AppContextComponent is already initialized."
      }

      appContextComponent = DaggerApplicationContextComponent
          .builder()
          .applicationContextModule(ApplicationContextModule(application))
          .build()
    }

    /**
     * Before use get() call init(context)
     */
    fun get(): ApplicationContextComponent {
      check(this::appContextComponent.isInitialized) {
        "AppContextComponent is not initialized yet. Call init first."
      }

      return appContextComponent
    }
  }

}
