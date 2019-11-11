package com.examples.movies.data.network

import com.examples.movies.data.entity.MovieDetailsEntity
import com.examples.movies.data.entity.MovieEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {
  companion object {
    private const val PARAM_MOVIE_ID = "movieId"
    private const val MOVIES = "movies.json"
    private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
  }

  @GET(MOVIES)
  fun movies(): Call<List<MovieEntity>>

  @GET(MOVIE_DETAILS)
  fun movieDetails(@Path(
      PARAM_MOVIE_ID) movieId: Int): Call<MovieDetailsEntity>
}
