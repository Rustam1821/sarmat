package com.rustamaliiev.sarmatapp.data.responses

import com.google.gson.annotations.SerializedName

data class ConfigurationResponse(
    @SerializedName("images")
    val images: ImageResponse

)
