package online.personaapp.core.presentation.navigation

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import online.personaapp.auth.presentation.Authenticator
import online.personaapp.auth.presentation.LoginActivity
import online.personaapp.core.extension.empty
import online.personaapp.movies.presentation.view.MovieDetailsActivity
import online.personaapp.movies.presentation.view.MoviesActivity
import online.personaapp.movies.presentation.viewstate.MovieView
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
@Singleton
class NavigatorImpl
@Inject constructor(private val authenticator: Authenticator) : Navigator {

  override fun showLogin(context: Context) = context.startActivity(
      LoginActivity.callingIntent(context))

  override fun showMain(context: Context) {
    when (authenticator.userLoggedIn()) {
      true -> showMovies(context)
      false -> showLogin(context)
    }
  }

  override fun showMovies(context: Context) = context.startActivity(
      MoviesActivity.callingIntent(context))

  override fun showMovieDetails(activity: Activity, bundle: Bundle,
      navigationExtras: Navigator.Extras) {
    val movie = bundle.getParcelable<MovieView>(
        MovieView.PARCELABLE_KEY)
        ?: throw IllegalArgumentException("movie argument not present")
    val intent = MovieDetailsActivity.callingIntent(activity, movie)
    val sharedView = navigationExtras.transitionSharedElement as ImageView
    val activityOptions = ActivityOptionsCompat
        .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
    activity.startActivity(intent, activityOptions.toBundle())
  }

  private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
  private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

  override fun openVideo(context: Context, videoUrl: String) {
    try {
      context.startActivity(createYoutubeIntent(videoUrl))
    } catch (ex: ActivityNotFoundException) {
      context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
    }
  }

  override fun createYoutubeIntent(videoUrl: String): Intent {
    val videoId = when {
      videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
      videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
      else -> videoUrl
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
    intent.putExtra("force_fullscreen", true)

    if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M)
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    return intent
  }

}


