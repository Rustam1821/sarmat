package com.rustamaliiev.sarmatapp

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.model.Movie


class VhMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvMovieName: TextView = itemView.findViewById(R.id.film_name)
    private val tvGenre: TextView = itemView.findViewById(R.id.genre)
    private val tvReviews: TextView = itemView.findViewById(R.id.reviews)
    private val tvAudienceLimit: TextView = itemView.findViewById(R.id.audience_limit)
    private val tvDuration: TextView = itemView.findViewById(R.id.duration)
    private val ivPoster: ImageView = itemView.findViewById(R.id.poster_color_image_view)
    private val rtRatingBar: RatingBar = itemView.findViewById(R.id.rating_bar)

    fun bind(movie: Movie, itemClickListener: (Movie) -> Unit) {
        itemView.setOnClickListener {
            itemClickListener(movie)
        }
        tvMovieName.text = movie.title
        tvGenre.text = movie.genres
        tvReviews.text = "${movie.reviewCount} reviews"
        tvAudienceLimit.text = "+${movie.pgAge}"
        tvDuration.text = "${movie.runningTime} min"
        ivPoster.setImageResource(movie.posterColorImageRes)
        rtRatingBar.rating = movie.rating
    }
}