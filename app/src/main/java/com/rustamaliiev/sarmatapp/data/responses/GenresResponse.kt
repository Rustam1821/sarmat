package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<GenreResponse>
)