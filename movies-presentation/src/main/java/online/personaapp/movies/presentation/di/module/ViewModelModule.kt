package online.personaapp.movies.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import online.personaapp.core.di.viewmodel.ViewModelFactory
import online.personaapp.core.di.viewmodel.ViewModelKey
import online.personaapp.movies.presentation.viewmodel.MovieDetailsViewModel
import online.personaapp.movies.presentation.viewmodel.MoviesViewModel

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
@Module
interface ViewModelModule {

  @Binds
  fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(MoviesViewModel::class)
  fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MovieDetailsViewModel::class)
  fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}