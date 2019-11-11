package online.personaapp.movies.di

import dagger.Binds
import dagger.Module
import online.personaapp.core.di.scope.PerFeature
import online.personaapp.movies.data.repository.MoviesRepositoryImpl
import online.personaapp.movies.domain.repository.MoviesRepository

@Module
interface MoviesRepositoryModule {

  @Binds
  @PerFeature
  fun provideMoviesRepository(dataSource: MoviesRepositoryImpl): MoviesRepository.Remote

}
