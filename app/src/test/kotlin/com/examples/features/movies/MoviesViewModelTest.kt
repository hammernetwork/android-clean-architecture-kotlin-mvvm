package com.examples.features.movies

import com.examples.AndroidTest
import com.examples.core.functional.Either.Right
import com.examples.features.movies.GetMovies
import com.examples.movies.domain.interactor.GetMovies
import com.examples.movies.domain.model.Movie
import com.examples.movies.presentation.viewmodel.MoviesViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesViewModelTest : AndroidTest() {

  private lateinit var moviesViewModel: MoviesViewModel

  @Mock
  private lateinit var getMovies: GetMovies

  @Before
  fun setUp() {
    moviesViewModel = MoviesViewModel(
        getMovies)
  }

  @Test
  fun `loading movies should update live data`() {
    val moviesList = listOf(Movie(0, "IronMan"),
        Movie(1, "Batman"))
    given { runBlocking { getMovies.run(eq(any())) } }.willReturn(Right(moviesList))

    moviesViewModel.movies.observeForever {
      it!!.size shouldEqualTo 2
      it[0].id shouldEqualTo 0
      it[0].poster shouldEqualTo "IronMan"
      it[1].id shouldEqualTo 1
      it[1].poster shouldEqualTo "Batman"
    }

    runBlocking { moviesViewModel.loadMovies() }
  }
}