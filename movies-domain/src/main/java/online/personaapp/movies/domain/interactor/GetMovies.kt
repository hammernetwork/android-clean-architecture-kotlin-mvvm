package online.personaapp.movies.domain.interactor

import online.personaapp.core.interactor.UseCase
import online.personaapp.core.interactor.UseCase.None
import online.personaapp.movies.domain.model.Movie
import online.personaapp.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovies
@Inject constructor(
    private val moviesRepository: MoviesRepository.Remote
) : UseCase<List<Movie>, None>() {

  override suspend fun run(params: None) = moviesRepository.movies()
}
