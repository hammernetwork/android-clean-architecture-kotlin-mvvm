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
package com.examples.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.examples.core.di.RouteActivityComponent
import com.examples.core.presentation.navigation.Navigator
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

  private val component: RouteActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
    RouteActivityComponent.get()
  }

  @Inject
  internal lateinit var navigator: Navigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    component.injectView(this)
    navigator.showMain(this)
  }

}
