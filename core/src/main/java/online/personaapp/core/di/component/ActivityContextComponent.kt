package online.personaapp.core.di.component

import android.content.Context
import dagger.Component
import online.personaapp.core.di.api.ActivityContextComponentApi
import online.personaapp.core.di.module.ActivityContextModule
import online.personaapp.core.di.scope.PerActivity

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
 * Component for activity context
 */
@PerActivity
@Component(modules = [ActivityContextModule::class])
interface ActivityContextComponent : ActivityContextComponentApi {

  companion object {

    /**
     * Method for initialize component
     */
    fun get(context: Context): ActivityContextComponent {
      return DaggerActivityContextComponent
          .builder()
          .activityContextModule(ActivityContextModule(context))
          .build()
    }
  }
}
