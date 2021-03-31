package com.rustamaliiev.sarmatapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiConfigurationResponse(
    @SerializedName("images")
    val images: ApiImageResponse
)
