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
package com.examples.features.movies

import android.graphics.Movie
import com.examples.UnitTest
import com.examples.core.functional.Either.Right
import com.examples.features.movies.GetMovies
import com.examples.movies.domain.interactor.GetMovies
import com.examples.movies.domain.model.Movie
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesTest : UnitTest() {

  private lateinit var getMovies: GetMovies

  @Mock
  private lateinit var moviesRepository: com.examples.movies.data.repository.MoviesRepository

  @Before
  fun setUp() {
    getMovies = GetMovies(moviesRepository)
    given { moviesRepository.movies() }.willReturn(Right(listOf(
        Movie.empty())))
  }

  @Test
  fun `should get data from repository`() {
    runBlocking { getMovies.run(com.examples.core.interactor.UseCase.None()) }

    verify(moviesRepository).movies()
    verifyNoMoreInteractions(moviesRepository)
  }
}
