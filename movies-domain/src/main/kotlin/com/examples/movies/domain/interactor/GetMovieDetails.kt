package com.examples.movies.domain.interactor

import com.examples.core.interactor.UseCase
import com.examples.movies.domain.interactor.GetMovieDetails.Params
import com.examples.movies.domain.model.MovieDetails
import com.examples.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieDetails
@Inject constructor(
    private val moviesRepository: MoviesRepository.Remote
) : UseCase<MovieDetails, Params>() {

  override suspend fun run(params: Params) = moviesRepository.movieDetails(params.id)

  data class Params(val id: Int)
}
