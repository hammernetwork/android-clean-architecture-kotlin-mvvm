package online.personaapp.movies.domain.interactor

import online.personaapp.core.interactor.UseCase
import online.personaapp.movies.domain.interactor.GetMovieDetails.Params
import online.personaapp.movies.domain.model.MovieDetails
import online.personaapp.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieDetails
@Inject constructor(
    private val moviesRepository: MoviesRepository.Remote
) : UseCase<MovieDetails, Params>() {

  override suspend fun run(params: Params) = moviesRepository.movieDetails(params.id)

  data class Params(val id: Int)
}
