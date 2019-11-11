package online.personaapp.movies.domain.model

import online.personaapp.core.extension.empty

data class Movie(val id: Int, val poster: String) {

  companion object {
    fun empty() = Movie(0, String.empty())
  }
}
