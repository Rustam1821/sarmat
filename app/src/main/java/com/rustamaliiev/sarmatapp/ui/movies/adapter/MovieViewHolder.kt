package com.rustamaliiev.sarmatapp.ui.movies.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.databinding.ViewHolderMovieBinding
import com.rustamaliiev.sarmatapp.domain.entities.Movie


class MovieViewHolder(private val binding: ViewHolderMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, itemClickListener: (Movie) -> Unit) {
        itemView.setOnClickListener {
            itemClickListener(movie)
        }
        with(binding) {
            filmName.text = movie.title
            genre.text = movie.genres.joinToString(", ") { it.name }
            reviews.text = itemView.context.getString(R.string.reviews, movie.reviewCount)
            audienceLimit.text = "+${movie.ageLimit}"
            duration.text = "${movie.runningTime} min"
            duration.isVisible = false

            Glide
                .with(binding.posterColorImageView)
                .load(movie.imageUrl)
                .centerCrop()
                .into(binding.posterColorImageView)

            ratingBar.rating = movie.rating.toFloat()
        }
    }
}