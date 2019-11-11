package com.examples.features.movies

import com.examples.UnitTest
import com.examples.core.functional.Either.Right
import com.examples.features.movies.GetMovies
import com.examples.movies.domain.interactor.GetMovies
import com.examples.movies.domain.model.Movie
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesTest : UnitTest() {

  private lateinit var getMovies: GetMovies

  @Mock
  private lateinit var moviesRepository: com.examples.movies.data.repository.MoviesRepository

  @Before
  fun setUp() {
    getMovies = GetMovies(moviesRepository)
    given { moviesRepository.movies() }.willReturn(Right(listOf(
        Movie.empty())))
  }

  @Test
  fun `should get data from repository`() {
    runBlocking { getMovies.run(com.examples.core.interactor.UseCase.None()) }

    verify(moviesRepository).movies()
    verifyNoMoreInteractions(moviesRepository)
  }
}
