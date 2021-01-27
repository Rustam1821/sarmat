package com.rustamaliiev.sarmatapp

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvMovieName: TextView = itemView.findViewById(R.id.film_name)
    private val tvGenre: TextView = itemView.findViewById(R.id.genre)
    private val tvReviews: TextView = itemView.findViewById(R.id.reviews)
    private val tvAudienceLimit: TextView = itemView.findViewById(R.id.audience_limit)
    private val tvDuration: TextView = itemView.findViewById(R.id.duration)
    private val ivPoster: ImageView = itemView.findViewById(R.id.poster_image_view)
    private val rtRatingBar: RatingBar = itemView.findViewById(R.id.rating_bar)


    fun bind(itemMovie: ItemMovie) {
        tvMovieName.text = itemMovie.movieName
        tvGenre.text = itemMovie.genre
        tvReviews.text = "${itemMovie.reviewsNumber} reviews"
        tvAudienceLimit.text = "+${itemMovie.audienceLimit}"
        tvDuration.text = "${itemMovie.duration} min"
        ivPoster.setImageResource(itemMovie.posterColorImageRes)
        rtRatingBar.rating = itemMovie.starsNumber
    }
}