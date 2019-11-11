package online.personaapp.core.di.component

import android.app.Application
import dagger.Component
import online.personaapp.core.di.api.ApplicationContextComponentApi
import online.personaapp.core.di.module.ApplicationContextModule
import javax.inject.Singleton

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
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
