package online.personaapp.core.di.proxy

import java.util.*

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
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
      componentMap[clazz] = factory.createComponent(clazz)
    }
    return componentMap[clazz] as T
  }

}
