package online.personaapp.movies.domain.repository

import online.personaapp.core.exception.Failure
import online.personaapp.core.functional.Either
import online.personaapp.movies.domain.model.Movie
import online.personaapp.movies.domain.model.MovieDetails

interface MoviesRepository {

  interface Remote {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>
  }

  interface Local

}
