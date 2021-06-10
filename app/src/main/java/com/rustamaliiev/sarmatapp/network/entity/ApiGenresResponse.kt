package com.rustamaliiev.sarmatapp.network.entity

import com.google.gson.annotations.SerializedName

data class ApiGenresResponse(
    @SerializedName("genres")
    val genres: List<ApiGenreResponse>
)