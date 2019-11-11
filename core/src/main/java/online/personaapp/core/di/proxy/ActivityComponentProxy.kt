package online.personaapp.core.di.proxy

import android.app.Activity
import android.content.Context
import java.util.*

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
 * Component proxy with caching map
 * Provide subclass of {@link [ComponentFactory]} for component creating
 */
object ActivityComponentProxy {

  val componentMap: WeakHashMap<Context, WeakHashMap<Class<*>, Any>> = WeakHashMap()
  val componentFactoryMap: WeakHashMap<Context, ComponentFactory> = WeakHashMap()

  /**
   * For use components activity scope, need to init factory first
   */
  fun Activity.initActivityComponentFactory(factory: ComponentFactory) {
    componentFactoryMap[this] = factory
  }

  /**
   * Release relation between context and factory, not necessary.
   */
  fun Activity.releaseActivityComponentFactory() {
    componentFactoryMap.remove(this)
  }

  /**
   * Init and get component api for activity scope
   */
  inline fun <reified T> getComponent(context: Context): T {
    val clazz = T::class.java
    val component = componentMap[context]?.get(clazz)
    return component as T ?: createComponentApi(clazz, context)
  }

  inline fun <reified T> createComponentApi(clazz: Class<T>, context: Context): T {
    val newComponentApi = componentFactoryMap[context]?.createComponent(clazz)
        ?: throw IllegalStateException("Init ComponentFactory first")

    // Create map if needed
    if (!componentMap.containsKey(context)) {
      componentMap[context] = WeakHashMap()
    }
    componentMap[context]?.put(clazz, newComponentApi)
    return newComponentApi as T
  }

}
