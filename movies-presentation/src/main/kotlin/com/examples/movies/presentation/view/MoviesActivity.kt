package com.examples.movies.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.examples.core.di.proxy.ActivityComponentProxy.initActivityComponentFactory
import com.examples.core.di.proxy.ActivityComponentProxy.releaseActivityComponentFactory
import com.examples.core.platform.BaseActivity
import com.examples.movies.presentation.di.factory.MoviesComponentFactoryImpl


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
