package com.examples.movies.domain.repository

import com.examples.core.exception.Failure
import com.examples.core.functional.Either
import com.examples.movies.domain.model.Movie
import com.examples.movies.domain.model.MovieDetails

interface MoviesRepository {

  interface Remote {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>
  }

  interface Local

}
