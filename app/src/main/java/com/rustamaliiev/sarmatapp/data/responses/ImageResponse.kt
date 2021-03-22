package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("base_url")
    val baseUrl: String,

    @SerializedName("poster_sizes")
    val posterSizes: List<String>,

    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>,

    @SerializedName("profile_sizes")
    val profileSizes: List<String>
)