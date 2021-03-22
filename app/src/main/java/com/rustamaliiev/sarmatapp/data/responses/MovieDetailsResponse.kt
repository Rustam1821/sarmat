package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genres")
    val genres: List<GenreResponse>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("revenue")
    val revenue: Int,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("adult")
    val isAdult: Boolean,
)