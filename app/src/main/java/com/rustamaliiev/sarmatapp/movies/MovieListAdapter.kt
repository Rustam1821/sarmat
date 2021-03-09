package com.rustamaliiev.sarmatapp.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.model.Movie
import com.rustamaliiev.sarmatapp.utils.inflate

class MovieListAdapter : RecyclerView.Adapter<VhMovie>() {

    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: (Movie) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhMovie {
        return VhMovie(
            parent.inflate(R.layout.view_holder_movie)
        )
    }

    override fun onBindViewHolder(holder: VhMovie, position: Int) {
        val movie = movies[position]
        holder.bind(movie, itemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}