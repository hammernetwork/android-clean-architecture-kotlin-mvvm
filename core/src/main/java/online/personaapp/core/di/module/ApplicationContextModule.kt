package online.personaapp.core.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
 * Module for application context
 */
@Module
class ApplicationContextModule(private val application: Application) {

  @Provides
  @Singleton
  fun provideApplicationContext(): Context = application

}
