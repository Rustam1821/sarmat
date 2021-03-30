package com.rustamaliiev.sarmatapp.network.entity

import com.google.gson.annotations.SerializedName

data class ApiMovieCastResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("cast")
    val apiCasts: List<ApiCastResponse>
)