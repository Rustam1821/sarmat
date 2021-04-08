package com.rustamaliiev.sarmatapp.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenres (
    @Embedded
    val movie: MovieDB,

//    @Relation(parentColumn = "movie_id", entityColumn = "genre_id")
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_id",
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val genres: List<GenreDB>
)