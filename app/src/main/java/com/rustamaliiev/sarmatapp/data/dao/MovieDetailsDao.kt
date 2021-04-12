package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.*
import com.rustamaliiev.sarmatapp.data.entity.*

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM movie_details WHERE movie_id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetailsActorPair

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(db: MovieDetailsDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPairs (movieWithActor: List<MovieWithActor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreDB>)

    @Transaction
    suspend fun insertMovieDetailsInDB(
        movieDB: MovieDetailsDB,
        actorsDB: List<ActorDB>,
        genresDB: List<GenreDB>,
        pairs: List<MovieWithActor>
    )
    {
        insertMovieDetails(movieDB)
        insertPairs(pairs)
        insertActors(actorsDB)
        insertGenres(genresDB)
    }

    @Query("SELECT EXISTS (SELECT 1 FROM movie_details WHERE movie_id = :id)")
    fun exists (id: Int): Boolean
}