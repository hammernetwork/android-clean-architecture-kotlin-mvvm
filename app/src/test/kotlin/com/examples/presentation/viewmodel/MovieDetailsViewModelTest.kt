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
package com.examples.presentation.viewmodel

import com.examples.core.AndroidTest
import com.examples.core.functional.Either.Right
import com.examples.movies.domain.interactor.GetMovieDetails
import com.examples.movies.domain.model.MovieDetails
import com.examples.movies.presentation.viewmodel.MovieDetailsViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MovieDetailsViewModelTest : AndroidTest() {

  private lateinit var movieDetailsViewModel: MovieDetailsViewModel

  @Mock
  private lateinit var getMovieDetails: GetMovieDetails

  @Before
  fun setUp() {
    movieDetailsViewModel = MovieDetailsViewModel(
        getMovieDetails)
  }

  @Test
  fun `loading movie details should update live data`() {
    val movieDetails = MovieDetails(0, "IronMan", "poster",
        "summary",
        "cast", "director", 2018, "trailer")
    given { runBlocking { getMovieDetails.run(eq(any())) } }.willReturn(Right(movieDetails))

    movieDetailsViewModel.movieDetails.observeForever {
      with(it!!) {
        id shouldEqualTo 0
        title shouldEqualTo "IronMan"
        poster shouldEqualTo "poster"
        summary shouldEqualTo "summary"
        cast shouldEqualTo "cast"
        director shouldEqualTo "director"
        year shouldEqualTo 2018
        trailer shouldEqualTo "trailer"
      }
    }

    runBlocking { movieDetailsViewModel.loadMovieDetails(0) }
  }
}