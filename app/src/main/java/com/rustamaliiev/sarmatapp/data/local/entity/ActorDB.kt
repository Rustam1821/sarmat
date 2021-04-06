package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Actor",
    foreignKeys = [ForeignKey(
        entity = MovieDB::class,
        parentColumns = ["movie_id"],
        childColumns = ["parent_id"],
        onDelete = ForeignKey.NO_ACTION
    )]
)

data class ActorDB(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String?,

    @ColumnInfo(name = "parent_id")
    val parentId: Int
)