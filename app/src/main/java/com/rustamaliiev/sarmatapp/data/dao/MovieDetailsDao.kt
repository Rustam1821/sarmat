package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rustamaliiev.sarmatapp.data.entity.MovieDetailsDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDetailsWithGenresAndActors

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM movie_details")
    suspend fun getMovieDetails(): MovieDetailsWithGenresAndActors

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(db: MovieDetailsDB)

    @Query("SELECT EXISTS (SELECT 1 FROM movie_details WHERE parent_id = :id)")
    fun exists (id: Int): Boolean
}