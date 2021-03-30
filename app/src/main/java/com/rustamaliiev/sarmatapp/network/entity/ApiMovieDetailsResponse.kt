package com.rustamaliiev.sarmatapp.network.entity

import com.google.gson.annotations.SerializedName

data class ApiMovieDetailsResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val title: String,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("adult")
    val isAdult: Boolean,

    @SerializedName("runtime")
    val runtime: Int?,

    @SerializedName("genres")
    val apiGenres: List<ApiGenreResponse>,

    )