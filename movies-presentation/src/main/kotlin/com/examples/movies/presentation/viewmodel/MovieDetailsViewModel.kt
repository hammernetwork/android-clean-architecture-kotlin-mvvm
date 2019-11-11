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