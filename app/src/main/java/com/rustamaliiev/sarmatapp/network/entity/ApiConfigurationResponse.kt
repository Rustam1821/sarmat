package com.rustamaliiev.sarmatapp.network.entity

import com.google.gson.annotations.SerializedName

data class ApiConfigurationResponse(
    @SerializedName("images")
    val images: ApiImageResponse
)
