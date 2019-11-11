package online.personaapp.movies.data.entity

import online.personaapp.movies.domain.model.Movie

data class MovieEntity(private val id: Int, private val poster: String) {
  fun toMovie() = Movie(id, poster)
}
