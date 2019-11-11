package online.personaapp.movies.presentation.di.api

import androidx.lifecycle.ViewModelProvider

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
interface MoviesViewModelFactoryComponentApi {
  fun viewModelFactory(): ViewModelProvider.Factory
}