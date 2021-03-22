package com.rustamaliiev.sarmatapp.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val storyLine: String,
//    val imageUrl: String,
    val detailImageUrl: String,
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val genres: List<Genre>,
    val actors: List<Actor>,
    val isLiked: Boolean
)
