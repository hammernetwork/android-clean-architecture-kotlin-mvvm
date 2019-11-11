package online.personaapp.movies.presentation.view

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import online.personaapp.core.exception.Failure
import online.personaapp.core.extension.*
import online.personaapp.core.platform.BaseFragment
import online.personaapp.core.presentation.navigation.Navigator
import online.personaapp.movies.domain.interactor.MovieFailure.ListNotAvailable
import online.personaapp.movies.presentation.R
import online.personaapp.movies.presentation.di.component.MoviesPresentationComponent
import online.personaapp.movies.presentation.viewmodel.MoviesViewModel
import online.personaapp.movies.presentation.viewstate.MovieView
import javax.inject.Inject

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
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
      is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
    }
  }

  private fun renderFailure(@StringRes message: Int) {
    movieList.invisible()
    emptyView.visible()
    hideProgress()
    notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
  }
}
