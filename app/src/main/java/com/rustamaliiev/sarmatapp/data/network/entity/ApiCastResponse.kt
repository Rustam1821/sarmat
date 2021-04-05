package com.rustamaliiev.sarmatapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiCastResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("profile_path")
    val profilePath: String
)
