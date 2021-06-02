package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "MovieWithGenre",
    primaryKeys = ["movie_id", "genre_id"]
)

data class MovieWithGenre(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "genre_id")
    val genreId: Int
)