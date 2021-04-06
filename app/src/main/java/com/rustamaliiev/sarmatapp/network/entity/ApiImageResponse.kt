package com.rustamaliiev.sarmatapp.network.entity

import com.google.gson.annotations.SerializedName

data class ApiImageResponse(
    @SerializedName("base_url")
    val baseUrl: String,
)