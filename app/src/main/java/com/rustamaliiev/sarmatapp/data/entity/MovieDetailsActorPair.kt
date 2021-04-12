package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.*

class MovieDetailsActorPair(
    @Embedded
    val movieDetails: MovieDetailsDB,

    @Relation(
        parentColumn = "movie_id",
        entity = ActorDB::class,
        entityColumn = "actor_id",
        associateBy = Junction(

            value = MovieWithActor::class,
            parentColumn = "movie_id",
            entityColumn = "actor_id"
        )
    )
    val actors: List<ActorDB>,

    @Relation(
        parentColumn = "movie_id",
        entity = GenreDB::class,
        entityColumn = "genre_id",
        associateBy = Junction(

            value = MovieWithGenre::class,
            parentColumn = "movie_id",
            entityColumn = "genre_id"
        )
    )
    val genres: List<GenreDB>,
)
