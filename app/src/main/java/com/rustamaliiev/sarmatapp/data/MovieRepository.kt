package com.rustamaliiev.sarmatapp.data

import android.content.Context
import com.rustamaliiev.sarmatapp.model.Actor
import com.rustamaliiev.sarmatapp.model.Genre
import com.rustamaliiev.sarmatapp.model.Movie
import com.rustamaliiev.sarmatapp.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface MovieRepository {
    suspend fun loadMovies(selector: String): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}
