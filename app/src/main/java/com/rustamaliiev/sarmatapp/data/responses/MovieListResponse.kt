package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieResponse>
)