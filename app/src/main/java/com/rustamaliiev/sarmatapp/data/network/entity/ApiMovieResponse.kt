package com.rustamaliiev.sarmatapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiMovieResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPicture: String,

    @SerializedName("backdrop_path")
    val backdropPicture: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val votesCount: Int,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("adult")
    val adult: Boolean
)

