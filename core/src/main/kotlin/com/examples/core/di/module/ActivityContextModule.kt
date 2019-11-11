package com.examples.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class ActivityContextModule(private val context: Context) {

  @Provides
  @com.examples.core.di.scope.PerActivity
  fun provideContext(): Context = context

}
