package com.rustamaliiev.sarmatapp.domain.repository

import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import kotlinx.coroutines.flow.Flow

interface BaseMovieRepository {
    suspend fun loadMovies(selector: String): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}