package com.examples.core.di.component

import android.content.Context
import com.examples.core.di.api.ActivityContextComponentApi
import com.examples.core.di.module.ActivityContextModule
import com.examples.core.di.scope.PerActivity
import dagger.Component

/**
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
