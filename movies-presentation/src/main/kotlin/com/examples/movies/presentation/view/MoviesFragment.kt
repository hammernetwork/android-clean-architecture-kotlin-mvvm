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
package com.examples.movies.presentation.view

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.examples.core.exception.Failure
import com.examples.core.extension.*
import com.examples.core.platform.BaseFragment
import com.examples.movies.domain.interactor.MovieFailure
import com.examples.movies.presentation.R
import com.examples.movies.presentation.di.component.MoviesPresentationComponent
import com.examples.movies.presentation.viewmodel.MoviesViewModel
import com.examples.movies.presentation.viewstate.MovieView
import com.examples.presentation.navigation.Navigator
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

  private lateinit var component: MoviesPresentationComponent

  @Inject
  lateinit var navigator: Navigator

  @Inject
  lateinit var moviesAdapter: MoviesAdapter

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var moviesViewModel: MoviesViewModel

  override fun layoutId() = R.layout.fragment_movies

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initInjection()

    moviesViewModel = viewModel(viewModelFactory) {
      observe(movies, ::renderMoviesList)
      failure(failure, ::handleFailure)
    }
  }

  private fun initInjection() {
    component = MoviesPresentationComponent.get(
        context ?: throw IllegalArgumentException("Context not available"))
    component.injectView(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeView()
    loadMoviesList()
  }

  private fun initializeView() {
    movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    movieList.adapter = moviesAdapter
    moviesAdapter.clickListener = { movie, navigationExtras ->
      val bundle = bundleOf(MovieView.PARCELABLE_KEY to movie)
      activity?.run {
        navigator.showMovieDetails(this, bundle, navigationExtras)
      }
    }
  }

  private fun loadMoviesList() {
    emptyView.invisible()
    movieList.visible()
    showProgress()
    moviesViewModel.loadMovies()
  }

  private fun renderMoviesList(movies: List<MovieView>?) {
    moviesAdapter.collection = movies.orEmpty()
    hideProgress()
  }

  private fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
      is Failure.ServerError -> renderFailure(R.string.failure_server_error)
      is MovieFailure.ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
    }
  }

  private fun renderFailure(@StringRes message: Int) {
    movieList.invisible()
    emptyView.visible()
    hideProgress()
    notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
  }
}
