package com.examples.movies.di

import com.examples.core.di.scope.PerFeature
import com.examples.movies.data.repository.MoviesRepositoryImpl
import com.examples.movies.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module

@Module
interface MoviesRepositoryModule {

  @Binds
  @PerFeature
  fun provideMoviesRepository(dataSource: MoviesRepositoryImpl): MoviesRepository.Remote

}
