package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieGenrePair (
    @Embedded
    val movie: MovieDB,

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
    val genres: List<GenreDB>
)