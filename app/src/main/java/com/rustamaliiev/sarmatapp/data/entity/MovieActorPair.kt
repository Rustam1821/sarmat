package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieActorPair(
    @Embedded
    val movie: MovieDetailsDB,

    @Relation(
        parentColumn = "movie_id",
        entity = GenreDB::class,
        entityColumn = "actor_id",
        associateBy = Junction(
            value = MovieWithActor::class,
            parentColumn = "movie_id",
            entityColumn = "actor_id"
        )
    )
    val actors: List<ActorDB>
)
