package com.examples.movies.domain.interactor

import com.examples.core.interactor.UseCase
import com.examples.core.interactor.UseCase.None
import com.examples.movies.domain.model.Movie
import com.examples.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovies
@Inject constructor(
    private val moviesRepository: MoviesRepository.Remote
) : UseCase<List<Movie>, None>() {

  override suspend fun run(params: None) = moviesRepository.movies()
}
