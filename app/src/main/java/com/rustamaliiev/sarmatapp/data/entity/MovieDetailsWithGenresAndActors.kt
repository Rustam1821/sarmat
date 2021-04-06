package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class MovieDetailsWithGenresAndActors (
    @Embedded
    val details: MovieDetailsDB,

    @Relation(entity = GenreDB::class ,parentColumn = "parent_id", entityColumn = "parent_id") //we don't need an entity
    val genres: List<GenreDB>,

    @Relation(parentColumn = "parent_id", entityColumn = "parent_id")
    val actors: List<ActorDB>
    )
