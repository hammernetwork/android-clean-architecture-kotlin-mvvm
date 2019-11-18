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