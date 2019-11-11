package com.examples.core.di.component

import android.content.Context
import dagger.Component

/**
 * Component for activity context
 */
@com.examples.core.di.scope.PerActivity
@Component(modules = [com.examples.core.di.module.ActivityContextModule::class])
interface ActivityContextComponent : com.examples.core.di.api.ActivityContextComponentApi {

  companion object {

    /**
     * Method for initialize component
     */
    fun get(context: Context): com.examples.core.di.component.ActivityContextComponent {
      return DaggerActivityContextComponent
          .builder()
          .activityContextModule(com.examples.core.di.module.ActivityContextModule(context))
          .build()
    }
  }
}
