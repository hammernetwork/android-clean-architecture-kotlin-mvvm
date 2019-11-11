package online.personaapp.movies.di

import online.personaapp.movies.domain.repository.MoviesRepository

interface MoviesComponentApi {
  fun moviesRepository(): MoviesRepository.Remote
}