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
package com.examples.core.di.proxy

import java.util.*

/**
 * Component proxy with caching map
 * Provide subclass of {@link [ComponentFactory]} for component creating
 */
object ComponentProxy {

  val componentMap: WeakHashMap<Class<*>, Any> = WeakHashMap()

  lateinit var factory: ComponentFactory

  fun init(factory: ComponentFactory) {
    ComponentProxy.factory = factory
  }

  /**
   * Init and get component api for application scope
   */
  inline fun <reified T> getComponent(): T {
    val clazz = T::class.java
    if (!componentMap.contains(clazz)) {
      componentMap[clazz] = factory.createComponent(
          clazz)
    }
    return componentMap[clazz] as T
  }

}
