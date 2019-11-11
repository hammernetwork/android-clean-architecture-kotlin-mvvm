package com.examples.movies.data.entity

import com.examples.movies.domain.model.Movie

data class MovieEntity(private val id: Int, private val poster: String) {
  fun toMovie() = Movie(id, poster)
}
