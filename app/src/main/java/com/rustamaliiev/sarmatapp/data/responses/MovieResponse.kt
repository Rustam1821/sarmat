package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPicture: String,

    @SerializedName("backdrop_path")
    val backdropPicture: String,

//    there's no runtime field in Json: https://developers.themoviedb.org/3/movies/get-top-rated-movies
//    but here is we have this field: https://developers.themoviedb.org/3/movies/get-movie-details
//    @SerializedName("runtime")
//    val runtime: Int,

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

