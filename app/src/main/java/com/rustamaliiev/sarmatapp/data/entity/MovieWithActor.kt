package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "MovieWithActor",
    primaryKeys = ["movie_id", "actor_id"]
)

data class MovieWithActor (
    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "actor_id")
    val actorId: Int
)

