package com.examples.features.movies

import com.examples.AndroidTest
import com.examples.core.functional.Either.Right
import com.examples.features.movies.GetMovieDetails
import com.examples.movies.domain.interactor.GetMovieDetails
import com.examples.movies.domain.model.MovieDetails
import com.examples.movies.presentation.viewmodel.MovieDetailsViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MovieDetailsViewModelTest : AndroidTest() {

  private lateinit var movieDetailsViewModel: MovieDetailsViewModel

  @Mock
  private lateinit var getMovieDetails: GetMovieDetails
  @Mock
  private lateinit var playMovie: PlayMovie

  @Before
  fun setUp() {
    movieDetailsViewModel = MovieDetailsViewModel(
        getMovieDetails,
        playMovie)
  }

  @Test
  fun `loading movie details should update live data`() {
    val movieDetails = MovieDetails(0, "IronMan", "poster",
        "summary",
        "cast", "director", 2018, "trailer")
    given { runBlocking { getMovieDetails.run(eq(any())) } }.willReturn(Right(movieDetails))

    movieDetailsViewModel.movieDetails.observeForever {
      with(it!!) {
        id shouldEqualTo 0
        title shouldEqualTo "IronMan"
        poster shouldEqualTo "poster"
        summary shouldEqualTo "summary"
        cast shouldEqualTo "cast"
        director shouldEqualTo "director"
        year shouldEqualTo 2018
        trailer shouldEqualTo "trailer"
      }
    }

    runBlocking { movieDetailsViewModel.loadMovieDetails(0) }
  }
}