package com.examples.movies.domain.interactor

import com.examples.core.exception.Failure

class MovieFailure {
  class ListNotAvailable : Failure.FeatureFailure()
  class NonExistentMovie : Failure.FeatureFailure()
}

