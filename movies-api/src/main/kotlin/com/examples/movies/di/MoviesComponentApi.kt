package com.examples.movies.di

import com.examples.movies.domain.repository.MoviesRepository

interface MoviesComponentApi {
  fun moviesRepository(): MoviesRepository.Remote
}