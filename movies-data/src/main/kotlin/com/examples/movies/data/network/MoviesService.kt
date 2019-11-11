package com.examples.movies.data.network

import retrofit2.Retrofit
import javax.inject.Inject

@com.examples.core.di.scope.PerFeature
class MoviesService
@Inject constructor(retrofit: Retrofit) : MoviesApi {
  private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

  override fun movies() = moviesApi.movies()
  override fun movieDetails(movieId: Int) = moviesApi.movieDetails(movieId)
}
