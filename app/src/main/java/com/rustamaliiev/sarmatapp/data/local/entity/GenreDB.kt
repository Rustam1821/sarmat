package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.*

@Entity(
    tableName = "genres",
    foreignKeys = [
        ForeignKey(
            entity = MovieDB::class,
            parentColumns = arrayOf("movieId"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Genre(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    val parentId: Int

)