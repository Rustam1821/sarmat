package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.*
import com.rustamaliiev.sarmatapp.data.entity.GenreDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDB
import com.rustamaliiev.sarmatapp.data.entity.MovieGenrePair
import com.rustamaliiev.sarmatapp.data.entity.MovieWithGenre
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.utils.getGenresDBs
import com.rustamaliiev.sarmatapp.utils.getMovieDBs
import com.rustamaliiev.sarmatapp.utils.getMovieGenrePairs

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE movie_group LIKE :movieGroup")
    suspend fun getMovies(movieGroup: String): List<MovieGenrePair>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(items: List<GenreDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieGenrePairs(movieWithGenre: List<MovieWithGenre>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(items: List<MovieDB>)

    @Transaction
    suspend fun insertMoviesInDb(moviesFromNet: List<Movie>, filmGroup: String) {
        val movies = getMovieDBs(moviesFromNet, filmGroup)
        val genres = getGenresDBs(moviesFromNet, filmGroup)
        val pairs = getMovieGenrePairs(moviesFromNet, filmGroup)

        insertMovies(movies)
        insertGenres(genres)
        insertMovieGenrePairs(pairs)
    }

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateMovies(items: List<MovieDB>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateGenres(items: List<GenreDB>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateMovieGenrePairs(movieWithGenre: List<MovieWithGenre>)

    @Transaction
    suspend fun updateMoviesInDb(moviesFromNet: List<Movie>, filmGroup: String) {
        val movies = getMovieDBs(moviesFromNet, filmGroup)
        val genres = getGenresDBs(moviesFromNet, filmGroup)
        val pairs = getMovieGenrePairs(moviesFromNet, filmGroup)

        updateMovies(movies)
        updateGenres(genres)
        updateMovieGenrePairs(pairs)
    }
}