package com.examples.core.di.proxy


interface ComponentFactory {
  fun createComponent(clazz: Class<*>): Any
}
