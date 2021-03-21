package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class TopRatedResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
)