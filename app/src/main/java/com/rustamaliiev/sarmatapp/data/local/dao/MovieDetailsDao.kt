package com.rustamaliiev.sarmatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.rustamaliiev.sarmatapp.data.local.entity.MovieDetailsBD
import com.rustamaliiev.sarmatapp.data.local.entity.MovieDetailsWithGenresAndActors

@Dao
interface MovieDetailsDao {
    @Query("Select * FROM movie_details")
    fun getMovieDetails(): MovieDetailsWithGenresAndActors
}