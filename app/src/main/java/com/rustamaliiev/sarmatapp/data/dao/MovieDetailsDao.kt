package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.*
import com.rustamaliiev.sarmatapp.data.entity.ActorDB
import com.rustamaliiev.sarmatapp.data.entity.GenreDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDetailsDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDetailsWithGenresAndActors

@Dao
interface MovieDetailsDao {
    @Transaction
    @Query("SELECT * FROM movie_details WHERE parent_id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetailsWithGenresAndActors

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(db: MovieDetailsDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreDB>)

    @Transaction
    open suspend fun insertDetailsWithGenresAndActors(moviedb: MovieDetailsDB,
                                              actorsDB: List<ActorDB>,
                                              genresDB: List<GenreDB>)
    {
        insertMovieDetails(moviedb)
        insertActors(actorsDB)
        insertGenres(genresDB)
    }

    @Query("SELECT EXISTS (SELECT 1 FROM movie_details WHERE parent_id = :id)")
    fun exists (id: Int): Boolean

    //insert actors
    //insert genres
}