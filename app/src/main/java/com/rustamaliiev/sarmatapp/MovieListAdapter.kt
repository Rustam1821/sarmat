package com.rustamaliiev.sarmatapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.utils.inflate
import com.rustamaliiev.sarmatapp.utils.movieListFilling

class MovieListAdapter : RecyclerView.Adapter<VhMovie>() {

    private var movies: MutableList<ItemMovie> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: (ItemMovie) -> Unit = {}

    init {
        movieListFilling(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhMovie {
        return VhMovie(
            parent.inflate(R.layout.view_holder_movie)
        )
    }

    override fun onBindViewHolder(holderVh: VhMovie, position: Int) {
        val movie = movies[position]
        holderVh.bind(movie, itemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}