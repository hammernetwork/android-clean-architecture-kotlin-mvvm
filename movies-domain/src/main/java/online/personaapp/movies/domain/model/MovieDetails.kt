package online.personaapp.movies.domain.model

import online.personaapp.core.extension.empty

data class MovieDetails(val id: Int,
    val title: String,
    val poster: String,
    val summary: String,
    val cast: String,
    val director: String,
    val year: Int,
    val trailer: String) {

  companion object {
    fun empty() = MovieDetails(0, String.empty(),
        String.empty(), String.empty(),
        String.empty(), String.empty(), 0, String.empty())
  }
}


