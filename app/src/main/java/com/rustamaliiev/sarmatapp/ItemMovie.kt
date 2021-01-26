package com.rustamaliiev.sarmatapp

import android.widget.ImageView
import android.widget.RatingBar

data class ItemMovie(
    val movieName: String,
    val genre: String,
    val reviewsNumber: Int,
    val audienceLimit: Int,
    val duration: Int,

    val posterColor_image_view: ImageView,
    val starsNumber: Float
)
