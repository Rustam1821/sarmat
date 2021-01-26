package com.rustamaliiev.sarmatapp

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvMovieName: TextView
    var tvGenre: TextView
    var tvReviews: TextView
    var tvAudienceLimit: TextView
    var tvDuration: TextView
    var ivPoster: ImageView
    var rtRatingBar: RatingBar

    init {
        tvMovieName = itemView.findViewById(R.id.film_name)
        tvGenre = itemView.findViewById(R.id.genre)
        tvReviews = itemView.findViewById(R.id.reviews)
        tvAudienceLimit = itemView.findViewById(R.id.audience_limit)
        tvDuration = itemView.findViewById(R.id.audience_limit)
        ivPoster = itemView.findViewById(R.id.poster_color_image_view)
        rtRatingBar = itemView.findViewById(R.id.rating_bar)
    }


    fun bind(itemMovie: ItemMovie) {
        tvMovieName.text = itemMovie.movieName
        tvGenre.text = itemMovie.genre
        tvReviews.text = "${itemMovie.reviewsNumber} reviews"
        tvAudienceLimit.text = "+${itemMovie.audienceLimit}"
        tvDuration.text = "${itemMovie.duration} min"
        // как правильно передавать картинку?
        ivPoster.setImageResource(itemMovie.posterColor_image_view.id)
        rtRatingBar.rating = itemMovie.starsNumber
    }
}