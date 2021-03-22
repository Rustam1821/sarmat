package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("cast")
    val casts: List<CastResponse>
)