package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rustamaliiev.sarmatapp.data.entity.MovieDB
import com.rustamaliiev.sarmatapp.data.entity.MovieWithGenres

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE movie_group LIKE :movieGroup")
    suspend fun getMovies(movieGroup: String): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(items: List<MovieDB>)
}