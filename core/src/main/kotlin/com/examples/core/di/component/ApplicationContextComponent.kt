package com.examples.core.di.component

import android.app.Application
import com.examples.core.di.api.ApplicationContextComponentApi
import com.examples.core.di.module.ApplicationContextModule
import dagger.Component
import javax.inject.Singleton

/**
 * Component for application context
 */
@Singleton
@Component(modules = [ApplicationContextModule::class])
interface ApplicationContextComponent : ApplicationContextComponentApi {

  companion object {
    @Volatile
    private lateinit var appContextComponent: ApplicationContextComponent

    /**
     * Method for initialize component
     */
    fun init(application: Application) {
      check(!this::appContextComponent.isInitialized) {
        "AppContextComponent is already initialized."
      }

      appContextComponent = DaggerApplicationContextComponent
          .builder()
          .applicationContextModule(ApplicationContextModule(application))
          .build()
    }

    /**
     * Before use get() call init(context)
     */
    fun get(): ApplicationContextComponent {
      check(this::appContextComponent.isInitialized) {
        "AppContextComponent is not initialized yet. Call init first."
      }

      return appContextComponent
    }
  }

}
