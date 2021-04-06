package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.*

@Entity(
    tableName = "Genre",
    foreignKeys = [
        ForeignKey(
            entity = MovieDB::class,
            parentColumns = ["movie_id"],
            childColumns = ["parent_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class GenreDB(
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    val id: Int,

    @ColumnInfo(name = "genre_name")
    val name: String,

    @ColumnInfo(name = "parent_id")
    val parentId: Int
)