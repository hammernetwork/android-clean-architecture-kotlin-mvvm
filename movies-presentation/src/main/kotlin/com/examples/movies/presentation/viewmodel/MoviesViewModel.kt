package com.examples.movies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.examples.core.interactor.UseCase.None
import com.examples.core.platform.BaseViewModel
import com.examples.movies.domain.interactor.GetMovies
import com.examples.movies.domain.model.Movie
import com.examples.movies.presentation.viewstate.MovieView
import javax.inject.Inject


class MoviesViewModel
@Inject constructor(
    private val getMovies: GetMovies
) : BaseViewModel() {

  var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

  fun loadMovies() = getMovies(None()) { it.either(::handleFailure, ::handleMovieList) }

  private fun handleMovieList(movies: List<Movie>) {
    this.movies.value = movies.map {
      MovieView(it.id, it.poster)
    }
  }

  override fun cancelRequest() {
    getMovies.cancel()
  }

}