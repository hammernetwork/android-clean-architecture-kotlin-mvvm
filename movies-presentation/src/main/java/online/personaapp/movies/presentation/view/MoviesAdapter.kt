package online.personaapp.movies.presentation.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_movie.view.*
import online.personaapp.core.extension.inflate
import online.personaapp.core.extension.loadFromUrl
import online.personaapp.core.presentation.navigation.Navigator
import online.personaapp.movies.presentation.R
import online.personaapp.movies.presentation.viewstate.MovieView
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

  internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ ->
    notifyDataSetChanged()
  }

  internal var clickListener: (MovieView, Navigator.Extras) -> Unit = { _, _ -> }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      ViewHolder(parent.inflate(R.layout.row_movie))

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
      viewHolder.bind(collection[position], clickListener)

  override fun getItemCount() = collection.size

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movieView: MovieView, clickListener: (MovieView, Navigator.Extras) -> Unit) {
      itemView.moviePoster.loadFromUrl(movieView.poster)
      itemView.setOnClickListener {
        clickListener(movieView, Navigator.Extras(itemView.moviePoster))
      }
    }
  }
}
