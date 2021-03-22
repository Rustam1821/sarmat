package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("poster_sizes")
    val posterSizes: List<String>,

    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>
)