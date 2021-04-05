package com.rustamaliiev.sarmatapp.domain.repository

import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails

interface MovieRepository {
    suspend fun loadMovies(selector: String): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}

