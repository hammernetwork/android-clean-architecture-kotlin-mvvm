package online.personaapp.features.movies

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import online.personaapp.UnitTest
import online.personaapp.core.functional.Either.Right
import online.personaapp.features.movies.GetMovies
import online.personaapp.movies.domain.interactor.GetMovies
import online.personaapp.movies.domain.model.Movie
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesTest : UnitTest() {

  private lateinit var getMovies: GetMovies

  @Mock
  private lateinit var moviesRepository: online.personaapp.movies.data.repository.MoviesRepository

  @Before
  fun setUp() {
    getMovies = GetMovies(moviesRepository)
    given { moviesRepository.movies() }.willReturn(Right(listOf(
        Movie.empty())))
  }

  @Test
  fun `should get data from repository`() {
    runBlocking { getMovies.run(online.personaapp.core.interactor.UseCase.None()) }

    verify(moviesRepository).movies()
    verifyNoMoreInteractions(moviesRepository)
  }
}
