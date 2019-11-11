package com.examples.movies.presentation.di.dependencies

import com.examples.movies.domain.repository.MoviesRepository

interface MoviesViewModelFactoryDependencies {
  fun moviesRepository(): MoviesRepository.Remote
}