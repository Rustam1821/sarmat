package com.rustamaliiev.sarmatapp.ui.movies.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.databinding.ViewHolderMovieBinding
import com.rustamaliiev.sarmatapp.domain.entity.Movie


class ViewHolderMovie(private val vhMovieBinding: ViewHolderMovieBinding) :
    RecyclerView.ViewHolder(vhMovieBinding.root) {

    fun bind(movie: Movie, itemClickListener: (Movie) -> Unit) {
        itemView.setOnClickListener {
            itemClickListener(movie)
        }
        with(vhMovieBinding) {
            filmName.text = movie.title
            genre.text = movie.genres.joinToString(", ") { it.name }
            reviews.text = "${movie.reviewCount} reviews"
            audienceLimit.text = "+${movie.ageLimit}"
            duration.text = "${movie.runningTime} min"
            duration.isVisible = false

            Glide
                .with(vhMovieBinding.posterColorImageView)
                .load(movie.imageUrl)
                .centerCrop()
                .into(vhMovieBinding.posterColorImageView)

            ratingBar.rating = movie.rating.toFloat()
        }
    }
}