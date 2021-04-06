package com.rustamaliiev.sarmatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rustamaliiev.sarmatapp.data.local.entity.MovieDetailsDB
import com.rustamaliiev.sarmatapp.data.local.entity.MovieDetailsWithGenresAndActors

@Dao
interface MovieDetailsDao {
    @Query("Select * FROM movie_details")
    fun getMovieDetails(): MovieDetailsWithGenresAndActors

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(ite: MovieDetailsDB)
}