package online.personaapp

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import online.personaapp.core.di.proxy.ComponentProxy
import online.personaapp.di.factory.ComponentFactoryImpl

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class PersonaApplication : Application() {

//    override val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
//        DaggerApplicationComponent
//                .builder()
//                .applicationContextModule(ApplicationContextModule(this))
//                .build()
//    }

  override fun onCreate() {
    super.onCreate()
    this.initializeInjector()
//        this.injectMembers()
    this.initializeLeakDetection()
  }

  private fun initializeInjector() {
    ComponentProxy.init(ComponentFactoryImpl(this))
  }

//    private fun injectMembers() = appComponent.inject(this)

  private fun initializeLeakDetection() {
    if (BuildConfig.DEBUG) LeakCanary.install(this)
  }
}
