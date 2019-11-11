package com.examples.core.di.proxy

import android.app.Activity
import android.content.Context
import java.util.*

/**
 * Component proxy with caching map
 * Provide subclass of {@link [ComponentFactory]} for component creating
 */
object ActivityComponentProxy {

  val componentMap: WeakHashMap<Context, WeakHashMap<Class<*>, Any>> = WeakHashMap()
  val componentFactoryMap: WeakHashMap<Context, com.examples.core.di.proxy.ComponentFactory> = WeakHashMap()

  /**
   * For use components activity scope, need to init factory first
   */
  fun Activity.initActivityComponentFactory(factory: com.examples.core.di.proxy.ComponentFactory) {
    com.examples.core.di.proxy.ActivityComponentProxy.componentFactoryMap[this] = factory
  }

  /**
   * Release relation between context and factory, not necessary.
   */
  fun Activity.releaseActivityComponentFactory() {
    com.examples.core.di.proxy.ActivityComponentProxy.componentFactoryMap.remove(this)
  }

  /**
   * Init and get component api for activity scope
   */
  inline fun <reified T> getComponent(context: Context): T {
    val clazz = T::class.java
    val component = com.examples.core.di.proxy.ActivityComponentProxy.componentMap[context]?.get(
        clazz)
    return component as T ?: com.examples.core.di.proxy.ActivityComponentProxy.createComponentApi(
        clazz, context)
  }

  inline fun <reified T> createComponentApi(clazz: Class<T>, context: Context): T {
    val newComponentApi = com.examples.core.di.proxy.ActivityComponentProxy.componentFactoryMap[context]?.createComponent(
        clazz)
        ?: throw IllegalStateException("Init ComponentFactory first")

    // Create map if needed
    if (!com.examples.core.di.proxy.ActivityComponentProxy.componentMap.containsKey(context)) {
      com.examples.core.di.proxy.ActivityComponentProxy.componentMap[context] = WeakHashMap()
    }
    com.examples.core.di.proxy.ActivityComponentProxy.componentMap[context]?.put(clazz,
        newComponentApi)
    return newComponentApi as T
  }

}
