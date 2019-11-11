package online.personaapp.movies.presentation.di.dependencies

import online.personaapp.movies.domain.repository.MoviesRepository

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
interface MoviesViewModelFactoryDependencies {
  fun moviesRepository(): MoviesRepository.Remote
}