package com.examples.movies.di

import com.examples.core.platform.NetworkHandler
import retrofit2.Retrofit

interface MoviesDependencies {
  fun retrofit(): Retrofit
  fun networkHandler(): NetworkHandler

}