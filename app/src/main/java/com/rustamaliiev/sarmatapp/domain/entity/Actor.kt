package com.rustamaliiev.sarmatapp.domain.entity

import java.io.Serializable

data class Actor(
    val id: Int,
    val name: String,
    val imageUrl: String?,
) : Serializable
