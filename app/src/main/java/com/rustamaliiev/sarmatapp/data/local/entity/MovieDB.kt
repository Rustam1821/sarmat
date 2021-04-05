package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies",
)

data class MovieDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movieId")
    val id: Int,

    val title: String,
    val imageUrl: String?,
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val runningTime: Int,
    val isLiked: Boolean
)
