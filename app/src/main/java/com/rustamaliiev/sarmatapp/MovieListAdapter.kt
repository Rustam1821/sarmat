package com.rustamaliiev.sarmatapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.utils.inflate
import com.rustamaliiev.sarmatapp.utils.movieListFilling

class MovieListAdapter() : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: MutableList<ItemMovie> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    init {
        movieListFilling(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            parent.inflate(R.layout.item_movie)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}