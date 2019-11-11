package com.examples.movies.data.repository

import com.examples.core.exception.Failure
import com.examples.core.functional.Either
import com.examples.core.functional.Either.Left
import com.examples.core.functional.Either.Right
import com.examples.core.platform.NetworkHandler
import com.examples.movies.data.entity.MovieDetailsEntity
import com.examples.movies.data.network.MoviesService
import com.examples.movies.domain.model.Movie
import com.examples.movies.domain.model.MovieDetails
import com.examples.movies.domain.repository.MoviesRepository
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
