package com.examples.core.di.component

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Component for application context
 */
@Singleton
@Component(modules = [com.examples.core.di.module.ApplicationContextModule::class])
interface ApplicationContextComponent : com.examples.core.di.api.ApplicationContextComponentApi {

  companion object {
    @Volatile
    private lateinit var appContextComponent: com.examples.core.di.component.ApplicationContextComponent

    /**
     * Method for initialize component
     */
    fun init(application: Application) {
      check(!this::appContextComponent.isInitialized) {
        "AppContextComponent is already initialized."
      }

      com.examples.core.di.component.ApplicationContextComponent.Companion.appContextComponent = DaggerApplicationContextComponent
          .builder()
          .applicationContextModule(
              com.examples.core.di.module.ApplicationContextModule(application))
          .build()
    }

    /**
     * Before use get() call init(context)
     */
    fun get(): com.examples.core.di.component.ApplicationContextComponent {
      check(this::appContextComponent.isInitialized) {
        "AppContextComponent is not initialized yet. Call init first."
      }

      return com.examples.core.di.component.ApplicationContextComponent.Companion.appContextComponent
    }
  }

}
