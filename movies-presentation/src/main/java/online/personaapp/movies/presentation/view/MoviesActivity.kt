package online.personaapp.movies.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import online.personaapp.core.di.proxy.ActivityComponentProxy.initActivityComponentFactory
import online.personaapp.core.di.proxy.ActivityComponentProxy.releaseActivityComponentFactory
import online.personaapp.core.platform.BaseActivity
import online.personaapp.movies.presentation.di.factory.MoviesComponentFactoryImpl

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class MoviesActivity : BaseActivity() {

  companion object {
    fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
  }

  override fun fragment() = MoviesFragment()

  override fun onCreate(savedInstanceState: Bundle?) {
    initActivityComponentFactory(MoviesComponentFactoryImpl(this))
    super.onCreate(savedInstanceState)
  }

  override fun onDestroy() {
    releaseActivityComponentFactory()
    super.onDestroy()
  }
}
