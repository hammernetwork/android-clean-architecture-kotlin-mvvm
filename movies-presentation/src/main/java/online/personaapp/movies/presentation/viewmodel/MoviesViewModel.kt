package online.personaapp.movies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import online.personaapp.core.interactor.UseCase.None
import online.personaapp.core.platform.BaseViewModel
import online.personaapp.movies.domain.interactor.GetMovies
import online.personaapp.movies.domain.model.Movie
import online.personaapp.movies.presentation.viewstate.MovieView
import javax.inject.Inject

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
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