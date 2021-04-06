package com.rustamaliiev.sarmatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rustamaliiev.sarmatapp.data.local.entity.MovieDB

@Dao
interface MovieDao {

    @Query("Select * FROM movies")
    fun getMovies(): List<MovieDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(items: List<MovieDB>)
}