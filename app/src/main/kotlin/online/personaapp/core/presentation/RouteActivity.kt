package online.personaapp.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import online.personaapp.core.di.RouteActivityComponent
import online.personaapp.core.presentation.navigation.Navigator
import javax.inject.Inject

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class RouteActivity : AppCompatActivity() {

  private val component: RouteActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
    RouteActivityComponent.get()
  }

  @Inject
  internal lateinit var navigator: Navigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    component.injectView(this)
    navigator.showMain(this)
  }

}
