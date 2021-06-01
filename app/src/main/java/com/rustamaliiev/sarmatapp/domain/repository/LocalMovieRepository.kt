package com.rustamaliiev.sarmatapp.domain.repository

import android.util.Log
import com.rustamaliiev.sarmatapp.data.AppDatabase
import com.rustamaliiev.sarmatapp.domain.entity.Actor
import com.rustamaliiev.sarmatapp.domain.entity.Genre
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.utils.mapMovieDetailsActorGenrePairToMovieDetails
import com.rustamaliiev.sarmatapp.utils.mapMovieGenrePairToMovie
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

class LocalMovieRepository(private val db: AppDatabase) : MovieRepository {
    private val movieDao by lazy { db.getMovieDao() }

    override suspend fun loadMovies(selector: String): List<Movie> =
        movieDao.getMovies(selector).map { movieGenrePair ->
            mapMovieGenrePairToMovie(movieGenrePair)
        }


    override suspend fun loadMoviesFlow(selector: String): Flow<List<Movie>> {
        val result = movieDao.getMoviesFlow(selector).map {
            it.map { movieGenrePair ->
                mapMovieGenrePairToMovie(movieGenrePair)
            }
        }
        return result
    }


    override suspend fun loadMovie(movieId: Int): MovieDetails =
        mapMovieDetailsActorGenrePairToMovieDetails(
            db.getMovieDetailsDao().getMovieDetails(movieId)
        )


    override suspend fun saveMovies(moviesFromNet: List<Movie>, filmGroup: String) {
        Log.i("QQQ", "Saving ${moviesFromNet.size} movies")
        movieDao.insertMoviesInDb(moviesFromNet, filmGroup)
    }

    override suspend fun updateMovies(movies: List<Movie>, movieGroup: String) {
        Log.i("UPD", "Updating ${movies.size} movies for the group $movieGroup")
        movieDao.updateMoviesInDb(movies, movieGroup)
    }

    override suspend fun saveMovieDetails(movieDetailsFromNet: MovieDetails) {
        Log.i(
            "QQQ",
            "Saving ${movieDetailsFromNet.title} with actors= ${movieDetailsFromNet.actors.size} and genres: ${
                movieDetailsFromNet.genres.map { it.name }.joinToString(", ")
            }"
        )
        db.getMovieDetailsDao().insertMovieDetailsInDB(movieDetailsFromNet)
    }

    override suspend fun deleteMovie(movieId: Int) {
        movieDao.deleteMovie(movieId)
    }
}