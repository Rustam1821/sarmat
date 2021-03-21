package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class GenresResponse(
    @SerializedName("genres")
    val genres: List<GenreResponse>
)