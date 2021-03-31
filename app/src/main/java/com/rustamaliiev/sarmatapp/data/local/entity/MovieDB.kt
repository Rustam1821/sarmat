package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.Entity
import com.rustamaliiev.sarmatapp.domain.entity.Genre
import java.time.Duration
@Entity(
    tableName = "Movie",
    primaryKeys = ["id"]
)

data class MovieDB(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val runningTime: Int,
    val isLiked: Boolean
)
