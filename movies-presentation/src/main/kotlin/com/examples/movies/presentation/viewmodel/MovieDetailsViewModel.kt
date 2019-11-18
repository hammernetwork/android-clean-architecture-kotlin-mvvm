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
package com.examples.movies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.examples.core.platform.BaseViewModel
import com.examples.movies.domain.interactor.GetMovieDetails
import com.examples.movies.domain.interactor.GetMovieDetails.Params
import com.examples.movies.domain.model.MovieDetails
import com.examples.movies.presentation.viewstate.MovieDetailsView
import javax.inject.Inject


class MovieDetailsViewModel
@Inject constructor(
    private val getMovieDetails: GetMovieDetails
) : BaseViewModel() {

  var movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()

  fun loadMovieDetails(movieId: Int) =
      getMovieDetails(Params(movieId)) { it.either(::handleFailure, ::handleMovieDetails) }

  private fun handleMovieDetails(movie: MovieDetails) {
    this.movieDetails.value = MovieDetailsView(
        movie.id,
        movie.title, movie.poster,
        movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
  }

  override fun cancelRequest() {
    getMovieDetails.cancel()
  }
}