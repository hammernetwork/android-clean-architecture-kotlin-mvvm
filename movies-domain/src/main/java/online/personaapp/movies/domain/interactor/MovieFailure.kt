package online.personaapp.movies.domain.interactor

import online.personaapp.core.exception.Failure

class MovieFailure {
  class ListNotAvailable : Failure.FeatureFailure()
  class NonExistentMovie : Failure.FeatureFailure()
}

