package com.rustamaliiev.sarmatapp.data

import com.rustamaliiev.sarmatapp.model.Movie

class NetworkMovieRepository: MovieRepository {
    private var baseUrl: String? = null
    private var posterSize: String? = null

    override suspend fun loadMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun loadMovie(movieId: Int): Movie? {
        TODO("Not yet implemented")
    }
}