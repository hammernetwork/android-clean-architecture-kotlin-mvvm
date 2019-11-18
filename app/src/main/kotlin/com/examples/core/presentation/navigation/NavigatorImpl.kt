/**
 * Copyright (C) 2018 Andriy Se Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.examples.core.presentation.navigation

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.examples.auth.presentation.Authenticator
import com.examples.auth.presentation.LoginActivity
import com.examples.core.extension.empty
import com.examples.movies.presentation.view.MovieDetailsActivity
import com.examples.movies.presentation.view.MoviesActivity
import com.examples.movies.presentation.viewstate.MovieView
import javax.inject.Inject
import javax.inject.Singleton

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


