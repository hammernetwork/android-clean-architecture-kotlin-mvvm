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
package com.examples.movies.domain.interactor

import com.examples.core.interactor.UseCase
import com.examples.core.interactor.UseCase.None
import com.examples.movies.domain.model.Movie
import com.examples.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovies
@Inject constructor(
    private val moviesRepository: MoviesRepository.Remote
) : UseCase<List<Movie>, None>() {

  override suspend fun run(params: None) = moviesRepository.movies()
}
