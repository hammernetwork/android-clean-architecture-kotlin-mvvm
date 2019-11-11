package com.examples.core.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module for application context
 */
@Module
class ApplicationContextModule(private val application: Application) {

  @Provides
  @Singleton
  fun provideApplicationContext(): Context = application

}
