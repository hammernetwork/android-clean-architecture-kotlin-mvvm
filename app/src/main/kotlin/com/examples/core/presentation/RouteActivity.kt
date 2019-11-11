package com.examples.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.examples.core.di.RouteActivityComponent
import com.examples.core.presentation.navigation.Navigator
import javax.inject.Inject

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
