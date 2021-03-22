package com.rustamaliiev.sarmatapp.model

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val runningTime: Int,
    val genres: List<Genre>,
    val isLiked: Boolean
)
