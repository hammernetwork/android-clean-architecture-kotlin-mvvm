package com.examples.movies.presentation.di.api

import androidx.lifecycle.ViewModelProvider


interface MoviesViewModelFactoryComponentApi {
  fun viewModelFactory(): ViewModelProvider.Factory
}