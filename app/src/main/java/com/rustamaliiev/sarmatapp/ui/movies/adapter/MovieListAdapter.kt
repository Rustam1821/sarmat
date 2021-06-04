package com.rustamaliiev.sarmatapp.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.databinding.ViewHolderMovieBinding
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.ui.movies.adapter.VhMovie
import com.rustamaliiev.sarmatapp.utils.inflate

class MovieListAdapter : RecyclerView.Adapter<VhMovie>() {

    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: (Movie) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhMovie =
        VhMovie(
            ViewHolderMovieBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: VhMovie, position: Int) {
        val movie = movies[position]
        holder.bind(movie, itemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
