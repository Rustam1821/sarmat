package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.*

@Entity(
    tableName = "genres",
)
data class GenreDB(
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    val id: Int,

    @ColumnInfo(name = "genre_name")
    val name: String,
)