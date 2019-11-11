package online.personaapp.movies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import online.personaapp.core.platform.BaseViewModel
import online.personaapp.movies.domain.interactor.GetMovieDetails
import online.personaapp.movies.domain.interactor.GetMovieDetails.Params
import online.personaapp.movies.domain.model.MovieDetails
import online.personaapp.movies.presentation.viewstate.MovieDetailsView
import javax.inject.Inject

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
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