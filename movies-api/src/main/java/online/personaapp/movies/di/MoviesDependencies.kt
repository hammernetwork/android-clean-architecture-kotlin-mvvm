package online.personaapp.movies.di

import online.personaapp.core.platform.NetworkHandler
import retrofit2.Retrofit

interface MoviesDependencies {
  fun retrofit(): Retrofit
  fun networkHandler(): NetworkHandler

}