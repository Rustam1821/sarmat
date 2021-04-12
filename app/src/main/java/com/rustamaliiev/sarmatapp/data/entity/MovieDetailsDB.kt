package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie_details",
    primaryKeys = ["movie_id"]
)

data class MovieDetailsDB(

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    val title: String,
    val storyLine: String?,
    val detailImageUrl: String?,
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val runTime: Int?,
)