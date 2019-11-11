package com.examples.movies.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examples.movies.presentation.viewmodel.MovieDetailsViewModel
import com.examples.movies.presentation.viewmodel.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

  @Binds
  fun bindViewModelFactory(
      factory: com.examples.core.di.viewmodel.ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @com.examples.core.di.viewmodel.ViewModelKey(MoviesViewModel::class)
  fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

  @Binds
  @IntoMap
  @com.examples.core.di.viewmodel.ViewModelKey(MovieDetailsViewModel::class)
  fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}