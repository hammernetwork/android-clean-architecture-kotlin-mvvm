package online.personaapp.movies.data.network

import online.personaapp.core.di.scope.PerFeature
import retrofit2.Retrofit
import javax.inject.Inject

@PerFeature
class MoviesService
@Inject constructor(retrofit: Retrofit) : MoviesApi {
  private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

  override fun movies() = moviesApi.movies()
  override fun movieDetails(movieId: Int) = moviesApi.movieDetails(movieId)
}
