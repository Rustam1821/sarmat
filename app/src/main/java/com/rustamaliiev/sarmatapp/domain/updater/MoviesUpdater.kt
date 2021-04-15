package com.rustamaliiev.sarmatapp.domain.updater

import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesUpdater {
    private val db = SarmatApp.db
    private val localMovieRepository: MovieRepository = LocalMovieRepository(db)
    private val remoteMovieRepository: MovieRepository = MoviesNetworkRepository()


    suspend fun updateMovies() {
        withContext(Dispatchers.IO) {
            db.getMovieDao().getSavedGroupNames().forEach { filmGroup ->
                val movies = remoteMovieRepository.loadMovies(filmGroup)
                localMovieRepository.updateMovies(movies, filmGroup)
            }
        }
    }
}