/**
 * Copyright (C) 2018 Andriy Se Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.examples.movies.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examples.core.di.viewmodel.ViewModelFactory
import com.examples.movies.presentation.viewmodel.MovieDetailsViewModel
import com.examples.movies.presentation.viewmodel.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

  @Binds
  fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @com.examples.core.di.viewmodel.ViewModelKey(MoviesViewModel::class)
  fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

  @Binds
  @IntoMap
  @com.examples.core.di.viewmodel.ViewModelKey(MovieDetailsViewModel::class)
  fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}