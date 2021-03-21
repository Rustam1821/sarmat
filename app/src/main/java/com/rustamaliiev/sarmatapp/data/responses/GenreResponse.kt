package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
