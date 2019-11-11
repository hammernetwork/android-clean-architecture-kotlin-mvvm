package online.personaapp.features.movies

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import online.personaapp.UnitTest
import online.personaapp.core.functional.Either.Right
import online.personaapp.features.movies.GetMovieDetails
import online.personaapp.movies.domain.interactor.GetMovieDetails
import online.personaapp.movies.domain.model.MovieDetails
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMovieDetailsTest : UnitTest() {

  private val MOVIE_ID = 1

  private lateinit var getMovieDetails: GetMovieDetails

  @Mock
  private lateinit var moviesRepository: online.personaapp.movies.data.repository.MoviesRepository

  @Before
  fun setUp() {
    getMovieDetails = GetMovieDetails(moviesRepository)
    given { moviesRepository.movieDetails(MOVIE_ID) }.willReturn(Right(
        MovieDetails.empty()))
  }

  @Test
  fun `should get data from repository`() {
    runBlocking { getMovieDetails.run(GetMovieDetails.Params(MOVIE_ID)) }

    verify(moviesRepository).movieDetails(MOVIE_ID)
    verifyNoMoreInteractions(moviesRepository)
  }
}
