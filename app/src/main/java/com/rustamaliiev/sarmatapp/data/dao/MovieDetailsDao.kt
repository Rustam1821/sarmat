package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.*
import com.rustamaliiev.sarmatapp.data.entity.*
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.utils.mapMovieDetailsToMovieDetailsBD

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM movie_details WHERE movie_id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetailsActorGenrePair

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(db: MovieDetailsDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertActorPairs(movieWithActor: List<MovieWithActor>)

    @Transaction
    suspend fun insertMovieDetailsInDB(movieDetails: MovieDetails) {

        val movieDetailsDB = mapMovieDetailsToMovieDetailsBD(movieDetails)

        val actorsDB = movieDetails.actors.map { actor ->
            ActorDB(
                id = actor.id,
                name = actor.name,
                imageUrl = actor.imageUrl,
            )
        }

        val moviesWithActors = movieDetails.actors.map { actor ->
            MovieWithActor(
                movieDetails.id,
                actorId = actor.id
            )
        }

        insertMovieDetails(movieDetailsDB)
        insertActorPairs(moviesWithActors)
        insertActors(actorsDB)
    }

    @Query("SELECT EXISTS (SELECT 1 FROM movie_details WHERE movie_id = :id)")
    fun exists(id: Int): Boolean
}
