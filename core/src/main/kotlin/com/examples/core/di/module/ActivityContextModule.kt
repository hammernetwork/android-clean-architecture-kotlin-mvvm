package com.examples.core.di.module

import android.content.Context
import com.examples.core.di.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule(private val context: Context) {

  @Provides
  @PerActivity
  fun provideContext(): Context = context

}
