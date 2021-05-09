package com.rustamaliiev.sarmatapp.domain.updater

import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesUpdater {
    private val db = SarmatApp.db
    private val localMovieRepository: MovieRepository = LocalMovieRepository(db)
    private val remoteMovieRepository: MovieRepository = MoviesNetworkRepository()
    private var newMovies = emptyList<Movie?>()


    suspend fun updateMovies() {
        withContext(Dispatchers.IO) {
            db.getMovieDao().getSavedGroupNames().forEach { filmGroup ->

                val savedMoviesList = localMovieRepository.loadMovies(filmGroup)
                val updatedMoviesList = remoteMovieRepository.loadMovies(filmGroup)
                newMovies += updatedMoviesList.minus(savedMoviesList)

                localMovieRepository.updateMovies(updatedMoviesList, filmGroup)
            }
        }
    }

    fun getNewMovieForNotification(): Movie?{
        return newMovies.sortedByDescending { it?.rating }.getOrNull(0)
    }
}