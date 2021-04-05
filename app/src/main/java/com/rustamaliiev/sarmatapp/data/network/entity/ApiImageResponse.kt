package com.rustamaliiev.sarmatapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiImageResponse(
    @SerializedName("base_url")
    val baseUrl: String,
)