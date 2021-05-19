package com.rustamaliiev.sarmatapp.domain.repository

import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails

interface MovieRepository {
    suspend fun loadMovies(selector: String): List<Movie>
    suspend fun saveMovies(movies: List<Movie>, movieGroup: String)
    suspend fun updateMovies(movies: List<Movie>, movieGroup: String)
    suspend fun loadMovie(movieId: Int): MovieDetails
    suspend fun saveMovieDetails(movieDetailsFromNet: MovieDetails)
    suspend fun deleteMovie(movieId: Int)
}

