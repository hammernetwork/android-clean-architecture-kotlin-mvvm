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
package com.examples.di.component

import com.examples.di.module.NavigationModule
import com.examples.presentation.navigation.di.api.NavigationComponentApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class])
interface NavigationComponent : NavigationComponentApi {
  companion object {
    /**
     * Method for create component
     */
    fun get(): NavigationComponent {
      return DaggerNavigationComponent
          .builder()
          .build()
    }

  }

}