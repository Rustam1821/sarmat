package com.rustamaliiev.sarmatapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiMovieListResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<ApiMovieResponse>
)