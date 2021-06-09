package com.rustamaliiev.sarmatapp.domain.repository

import com.rustamaliiev.sarmatapp.data.AppDatabase
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.utils.mapMovieDetailsActorGenrePairToMovieDetails
import com.rustamaliiev.sarmatapp.utils.mapMovieGenrePairToMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalMovieRepository(private val db: AppDatabase) : CrudMovieRepository {
    private val movieDao by lazy { db.getMovieDao() }
    private val movieDetailsDao by lazy { db.getMovieDetailsDao() }

    override suspend fun loadMovies(selector: String): List<Movie> {
        return movieDao.getMoviesFlow(selector).first().map { movieGenrePair ->
            mapMovieGenrePairToMovie(movieGenrePair)
        }
    }

    override suspend fun observeMovies(selector: String): Flow<List<Movie>> =
        movieDao.getMoviesFlow(selector).map {
            it.map { movieGenrePair ->
                mapMovieGenrePairToMovie(movieGenrePair)
            }
        }

    override suspend fun loadMovie(movieId: Int): MovieDetails =
        mapMovieDetailsActorGenrePairToMovieDetails(
            movieDetailsDao.getMovieDetails(movieId)
        )

    override suspend fun saveMovies(moviesFromNet: List<Movie>, filmGroup: String) {
        movieDao.insertMoviesInDb(moviesFromNet, filmGroup)
    }

    override suspend fun updateMovies(movies: List<Movie>, movieGroup: String) {
        movieDao.updateMoviesInDb(movies, movieGroup)
    }

    override suspend fun saveMovieDetails(movieDetailsFromNet: MovieDetails) {
        movieDetailsDao.insertMovieDetailsInDB(movieDetailsFromNet)
    }

    override suspend fun deleteMovie(movieId: Int) {
        movieDao.deleteMovie(movieId)
    }
}