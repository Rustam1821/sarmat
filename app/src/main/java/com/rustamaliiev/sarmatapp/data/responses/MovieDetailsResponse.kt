package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class MovieDetailsResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genres") val genres: List<GenreResponse>,
    @SerializedName("id") val id: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int?,
)