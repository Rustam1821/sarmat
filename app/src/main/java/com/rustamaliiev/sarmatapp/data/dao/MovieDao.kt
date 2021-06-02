package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.*
import com.rustamaliiev.sarmatapp.data.entity.GenreDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDB
import com.rustamaliiev.sarmatapp.data.entity.MovieGenrePair
import com.rustamaliiev.sarmatapp.data.entity.MovieWithGenre
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.utils.mapGenreDomainToDB
import com.rustamaliiev.sarmatapp.utils.mapMovieDomainToDB
import com.rustamaliiev.sarmatapp.utils.mapToMovieGenrePairs
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("DELETE FROM movies WHERE movie_id LIKE :movieId")
    suspend fun deleteMovie(movieId: Int)

    //if suspend -> "error: Not sure how to convert a Cursor to this method's return type"
    @Query("SELECT * FROM movies WHERE movie_group LIKE :movieGroup ORDER BY movie_rating DESC")
    fun getMoviesFlow(movieGroup: String): Flow<List<MovieGenrePair>>

    @Query("SELECT movie_group FROM movies GROUP BY movie_group")
    suspend fun getSavedGroupNames(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(items: List<GenreDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieGenrePairs(movieWithGenre: List<MovieWithGenre>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(items: List<MovieDB>)

    @Transaction
    suspend fun insertMoviesInDb(moviesFromNet: List<Movie>, filmGroup: String) {
        val movies = mapMovieDomainToDB(moviesFromNet, filmGroup)
        val genres = mapGenreDomainToDB(moviesFromNet)
        val pairs = mapToMovieGenrePairs(moviesFromNet)

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
        val movies = mapMovieDomainToDB(moviesFromNet, filmGroup)
        val genres = mapGenreDomainToDB(moviesFromNet)
        val pairs = mapToMovieGenrePairs(moviesFromNet)

        updateMovies(movies)
        updateGenres(genres)
        updateMovieGenrePairs(pairs)
    }
}