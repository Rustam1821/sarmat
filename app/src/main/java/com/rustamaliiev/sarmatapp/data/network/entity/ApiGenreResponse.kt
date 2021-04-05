package com.rustamaliiev.sarmatapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiGenreResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
