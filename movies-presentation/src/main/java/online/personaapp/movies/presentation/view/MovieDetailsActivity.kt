package online.personaapp.movies.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import online.personaapp.core.di.proxy.ActivityComponentProxy.initActivityComponentFactory
import online.personaapp.core.di.proxy.ActivityComponentProxy.releaseActivityComponentFactory
import online.personaapp.core.platform.BaseActivity
import online.personaapp.movies.presentation.di.factory.MoviesComponentFactoryImpl
import online.personaapp.movies.presentation.viewstate.MovieView

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class MovieDetailsActivity : BaseActivity() {

  companion object {
    private const val INTENT_EXTRA_PARAM_MOVIE = "online.personaapp.INTENT_PARAM_MOVIE"

    fun callingIntent(context: Context, movie: MovieView): Intent {
      val intent = Intent(context, MovieDetailsActivity::class.java)
      intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movie)
      return intent
    }
  }

  override fun fragment(): MovieDetailsFragment {
    val movie = intent.getParcelableExtra<MovieView>(
        INTENT_EXTRA_PARAM_MOVIE) ?: throw IllegalStateException()
    return MovieDetailsFragment.forMovie(movie)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    initActivityComponentFactory(MoviesComponentFactoryImpl(this))
    super.onCreate(savedInstanceState)
  }

  override fun onDestroy() {
    releaseActivityComponentFactory()
    super.onDestroy()
  }
}
