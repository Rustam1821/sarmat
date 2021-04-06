package com.rustamaliiev.sarmatapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class MovieWithGenres (
    @Embedded
    val movie: MovieDB,

    @Relation(parentColumn = "movie_id", entityColumn = "parent_id")
    val genres: List<GenreDB>
)