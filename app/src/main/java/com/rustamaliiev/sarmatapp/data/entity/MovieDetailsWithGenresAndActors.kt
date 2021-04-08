package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class MovieDetailsWithGenresAndActors (
    @Embedded
    val details: MovieDetailsDB,

    @Relation(parentColumn = "parent_id", entityColumn = "genre_id")
    val genres: List<GenreDB>,

    @Relation(parentColumn = "parent_id", entityColumn = "actor_id")
    val actors: List<ActorDB>
    )
