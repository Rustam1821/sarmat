package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.*

@Entity(
    tableName = "Genre",
    foreignKeys = [
        ForeignKey(
            entity = MovieDB::class,
            parentColumns = ["movieId"],
            childColumns = ["parentId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class GenreDB(
    @PrimaryKey
    @ColumnInfo(name = "genreId")
    val id: Int,

    @ColumnInfo(name = "genreName")
    val name: String,

    val parentId: Int

)