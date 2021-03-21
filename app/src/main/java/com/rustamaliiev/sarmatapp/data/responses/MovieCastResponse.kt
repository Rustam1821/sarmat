package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MovieCastResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("cast")
    val casts: List<CastResponse>
)