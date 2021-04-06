package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie_details",
    foreignKeys = [ForeignKey(
        entity = MovieDB::class,
        parentColumns = ["movie_id"],
        childColumns = ["parent_id"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class MovieDetailsBD(
    @PrimaryKey
    @ColumnInfo(name = "parent_id")
    val parentId: Int,

    val title: String,
    val backdropPath: String?,
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val isLiked: Boolean,
    val overview: String?,
)