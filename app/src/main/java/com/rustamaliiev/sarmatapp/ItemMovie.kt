package com.rustamaliiev.sarmatapp

import androidx.annotation.DrawableRes

data class ItemMovie(
    val movieName: String,
    val genre: String,
    val reviewsNumber: Int,
    val audienceLimit: Int,
    val duration: Int,

    @DrawableRes
    val posterColorImageRes: Int,
    val starsNumber: Float
)
