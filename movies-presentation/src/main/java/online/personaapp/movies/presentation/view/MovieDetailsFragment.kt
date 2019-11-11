package online.personaapp.movies.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_movie_details.*
import online.personaapp.core.exception.Failure
import online.personaapp.core.extension.*
import online.personaapp.core.platform.BaseActivity
import online.personaapp.core.platform.BaseFragment
import online.personaapp.core.presentation.navigation.Navigator
import online.personaapp.movies.domain.interactor.MovieFailure
import online.personaapp.movies.presentation.R
import online.personaapp.movies.presentation.animator.MovieDetailsAnimator
import online.personaapp.movies.presentation.di.component.MovieDetailsPresentationComponent
import online.personaapp.movies.presentation.viewmodel.MovieDetailsViewModel
import online.personaapp.movies.presentation.viewstate.MovieDetailsView
import online.personaapp.movies.presentation.viewstate.MovieView
import javax.inject.Inject

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class MovieDetailsFragment : BaseFragment() {

  private lateinit var component: MovieDetailsPresentationComponent

  companion object {
    private const val PARAM_MOVIE = "param_movie"

    fun forMovie(movie: MovieView): MovieDetailsFragment {
      val movieDetailsFragment = MovieDetailsFragment()
      val arguments = bundleOf(
          PARAM_MOVIE to movie
      )
      movieDetailsFragment.arguments = arguments

      return movieDetailsFragment
    }
  }

  @Inject
  lateinit var movieDetailsAnimator: MovieDetailsAnimator

  @Inject
  lateinit var navigator: Navigator

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var movieDetailsViewModel: MovieDetailsViewModel

  override fun layoutId() = R.layout.fragment_movie_details

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initInjection()
    activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }

    movieDetailsViewModel = viewModel(viewModelFactory) {
      observe(movieDetails, ::renderMovieDetails)
      failure(failure, ::handleFailure)
    }
  }

  private fun initInjection() {
    component = MovieDetailsPresentationComponent.get(
        context ?: throw IllegalArgumentException("Context not available"))
    component.injectView(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (firstTimeCreated(savedInstanceState)) {
      movieDetailsViewModel.loadMovieDetails((arguments?.get(
          PARAM_MOVIE) as MovieView).id)
    } else {
      movieDetailsAnimator.scaleUpView(moviePlay)
      movieDetailsAnimator.cancelTransition(moviePoster)
      moviePoster.loadFromUrl((arguments!![PARAM_MOVIE] as MovieView).poster)
    }
  }

  override fun onBackPressed() {
    movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
    if (moviePlay.isVisible())
      movieDetailsAnimator.scaleDownView(moviePlay)
    else
      movieDetailsAnimator.cancelTransition(moviePoster)
  }

  private fun renderMovieDetails(movie: MovieDetailsView?) {
    movie?.let {
      with(movie) {
        (activity as? BaseActivity)?.let {
          moviePoster.loadUrlAndPostponeEnterTransition(poster, it)
          it.getToolbar().title = title
        }
        movieSummary.text = summary
        movieCast.text = cast
        movieDirector.text = director
        movieYear.text = year.toString()
        moviePlay.setOnClickListener {
          navigator.openVideo(context ?: return@setOnClickListener, trailer)
        }
      }
    }
    movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
    movieDetailsAnimator.scaleUpView(moviePlay)
  }

  private fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> {
        notify(R.string.failure_network_connection); close()
      }
      is Failure.ServerError -> {
        notify(R.string.failure_server_error); close()
      }
      is MovieFailure.NonExistentMovie -> {
        notify(R.string.failure_movie_non_existent); close()
      }
    }
  }
}
