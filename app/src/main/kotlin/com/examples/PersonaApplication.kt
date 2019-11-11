package com.examples

import android.app.Application
import com.examples.di.factory.ComponentFactoryImpl
import com.squareup.leakcanary.LeakCanary

class PersonaApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    this.initializeInjector()
    this.initializeLeakDetection()
  }

  private fun initializeInjector() {
    com.examples.core.di.proxy.ComponentProxy.init(ComponentFactoryImpl(this))
  }

  private fun initializeLeakDetection() {
    if (BuildConfig.DEBUG) LeakCanary.install(this)
  }
}
