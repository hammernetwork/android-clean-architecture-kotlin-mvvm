package online.personaapp.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import online.personaapp.core.di.scope.PerActivity

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
@Module
class ActivityContextModule(private val context: Context) {

  @Provides
  @PerActivity
  fun provideContext(): Context = context

}
