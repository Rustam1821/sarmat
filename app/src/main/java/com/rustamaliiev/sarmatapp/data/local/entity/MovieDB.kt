package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "movies",

)

data class MovieDB(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @Relation(parentColumn = "sd", entityColumn = "sdfsd")
    val title: String,
    val imageUrl: String?,
    val rating: Double,
    val reviewCount: Int,
    val ageLimit: Int,
    val runningTime: Int,
    val isLiked: Boolean
)
