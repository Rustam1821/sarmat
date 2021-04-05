package com.rustamaliiev.sarmatapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiGenresResponse(
    @SerializedName("genres")
    val apiGenres: List<ApiGenreResponse>
)