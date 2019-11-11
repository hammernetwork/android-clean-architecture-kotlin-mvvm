package com.examples.core.di.proxy

import java.util.*

/**
 * Component proxy with caching map
 * Provide subclass of {@link [ComponentFactory]} for component creating
 */
object ComponentProxy {

  val componentMap: WeakHashMap<Class<*>, Any> = WeakHashMap()

  lateinit var factory: com.examples.core.di.proxy.ComponentFactory

  fun init(factory: com.examples.core.di.proxy.ComponentFactory) {
    com.examples.core.di.proxy.ComponentProxy.factory = factory
  }

  /**
   * Init and get component api for application scope
   */
  inline fun <reified T> getComponent(): T {
    val clazz = T::class.java
    if (!com.examples.core.di.proxy.ComponentProxy.componentMap.contains(clazz)) {
      com.examples.core.di.proxy.ComponentProxy.componentMap[clazz] = com.examples.core.di.proxy.ComponentProxy.factory.createComponent(
          clazz)
    }
    return com.examples.core.di.proxy.ComponentProxy.componentMap[clazz] as T
  }

}
