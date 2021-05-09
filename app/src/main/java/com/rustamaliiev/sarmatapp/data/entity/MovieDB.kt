package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies",
    primaryKeys = ["movie_id", "movie_group"]
)

data class MovieDB(
    @ColumnInfo(name = "movie_id")
    val id: Int,

    val title: String,
    val imageUrl: String?,

    @ColumnInfo(name = "movie_rating")
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val runningTime: Int,
    val isLiked: Boolean,
    @ColumnInfo(name = "movie_group")
    val filmGroups: String
)
