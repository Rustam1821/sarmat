package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "actors",
)

data class ActorDB(
    @PrimaryKey
    @ColumnInfo(name = "actor_id")
    val id: Int,

    val name: String,
    val imageUrl: String?,

)