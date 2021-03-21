package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CastResponse (

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("profile_path")
    val  profilePath: String
    )