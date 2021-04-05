package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Actor",
    foreignKeys = [ForeignKey(
        entity = MovieDB::class,
        parentColumns = ["movieId"],
        childColumns = ["parentId"],
        onDelete = ForeignKey.NO_ACTION
    )]
)

data class ActorDB(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String?,


    val parentId: Int


)