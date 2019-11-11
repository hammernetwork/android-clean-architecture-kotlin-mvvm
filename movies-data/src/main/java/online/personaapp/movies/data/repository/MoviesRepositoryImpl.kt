package online.personaapp.movies.data.repository

import online.personaapp.core.exception.Failure
import online.personaapp.core.functional.Either
import online.personaapp.core.functional.Either.Left
import online.personaapp.core.functional.Either.Right
import online.personaapp.core.platform.NetworkHandler
import online.personaapp.movies.data.entity.MovieDetailsEntity
import online.personaapp.movies.data.network.MoviesService
import online.personaapp.movies.domain.model.Movie
import online.personaapp.movies.domain.model.MovieDetails
import online.personaapp.movies.domain.repository.MoviesRepository
import retrofit2.Call
import javax.inject.Inject

class MoviesRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: MoviesService
) : MoviesRepository.Remote {

  override fun movies(): Either<Failure, List<Movie>> {
    return when (networkHandler.isConnected) {
      true -> request(service.movies(), { it.map { it.toMovie() } }, emptyList())
      false -> Left(Failure.NetworkConnection)
    }
  }

  override fun movieDetails(
      movieId: Int): Either<Failure, MovieDetails> {
    return when (networkHandler.isConnected) {
      true -> request(service.movieDetails(movieId), { it.toMovieDetails() },
          MovieDetailsEntity.empty())
      false -> Left(Failure.NetworkConnection)
    }
  }

  private fun <T, R> request(call: Call<T>, transform: (T) -> R,
      default: T): Either<Failure, R> {
    return try {
      val response = call.execute()
      when (response.isSuccessful) {
        true -> Right(transform((response.body() ?: default)))
        false -> Left(Failure.ServerError)
      }
    } catch (exception: Throwable) {
      Left(Failure.ServerError)
    }
  }
}
