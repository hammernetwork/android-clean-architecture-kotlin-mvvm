package com.examples.movies.domain.model

import com.examples.core.extension.empty

data class Movie(val id: Int, val poster: String) {

  companion object {
    fun empty() = Movie(0, String.empty())
  }
}
