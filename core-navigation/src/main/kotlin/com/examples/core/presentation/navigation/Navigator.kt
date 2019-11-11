package com.examples.core.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View

interface Navigator {
  fun showLogin(context: Context)
  fun showMain(context: Context)
  fun showMovies(context: Context)
  fun showMovieDetails(activity: Activity, bundle: Bundle, navigationExtras: Extras)
  fun openVideo(context: Context, videoUrl: String)
  fun createYoutubeIntent(videoUrl: String): Intent
  class Extras(val transitionSharedElement: View)
}