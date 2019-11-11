package com.examples.movies.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.examples.core.di.proxy.ActivityComponentProxy.initActivityComponentFactory
import com.examples.core.di.proxy.ActivityComponentProxy.releaseActivityComponentFactory
import com.examples.core.platform.BaseActivity
import com.examples.movies.presentation.di.factory.MoviesComponentFactoryImpl
import com.examples.movies.presentation.viewstate.MovieView

class MovieDetailsActivity : BaseActivity() {

  companion object {
    private const val INTENT_EXTRA_PARAM_MOVIE = "com.examples.INTENT_PARAM_MOVIE"

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
