package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("base_url")
    val baseUrl: String,
)