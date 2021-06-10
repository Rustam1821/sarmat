package com.rustamaliiev.sarmatapp.domain.repository

import com.rustamaliiev.sarmatapp.domain.entities.Movie
import com.rustamaliiev.sarmatapp.domain.entities.MovieDetails

interface BaseMovieRepository {
    suspend fun loadMovies(selector: String): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}