package com.rustamaliiev.sarmatapp.domain.repository

import android.util.Log
import com.rustamaliiev.sarmatapp.data.AppDatabase
import com.rustamaliiev.sarmatapp.data.entity.*
import com.rustamaliiev.sarmatapp.domain.entity.Actor
import com.rustamaliiev.sarmatapp.domain.entity.Genre
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails

class LocalMovieRepository(private val db: AppDatabase) : MovieRepository {

    override suspend fun loadMovies(selector: String): List<Movie> {
        //TODO simplify it
        Log.e("QQQ", "LocalMovieRepository, load list of movies")
        return db.getMovieDao().getMovies(selector).map { movieDB ->

            Movie(
                id = movieDB.movie.id,
                title = movieDB.movie.title,
                imageUrl = movieDB.movie.imageUrl,
                rating = movieDB.movie.rating,
                reviewCount = movieDB.movie.reviewCount,
                ageLimit = movieDB.movie.ageLimit,
                runningTime = movieDB.movie.runningTime,
                genres = movieDB.genres.map { genreDB ->
                    Genre(genreDB.id, genreDB.name)
                },
                isLiked = movieDB.movie.isLiked
            )
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return with(db.getMovieDetailsDao().getMovieDetails(movieId)) {
            Log.e("QQQ1", "LocalMovieRepository, load movie details, actors size: ${actors.size}")
            MovieDetails(
                id = movieDetails.movieId,
                title = movieDetails.title,
                storyLine = movieDetails.storyLine ?: "",
                detailImageUrl = movieDetails.detailImageUrl,
                rating = movieDetails.rating,
                reviewCount = movieDetails.reviewCount,
                ageLimit = movieDetails.ageLimit,
                runtime = movieDetails.runTime ?: 0,
                genres = genres.map { genreDB ->
                    Genre(genreDB.id, genreDB.name)
                },
                actors = actors.map { actorDB ->
                    Actor(actorDB.id, actorDB.name, actorDB.imageUrl)
                }
            )
        }
    }

    override suspend fun saveMovies(moviesFromNet: List<Movie>, filmGroup: String) {
        db.getMovieDao().insertMoviesInDb(moviesFromNet, filmGroup)
    }

    override suspend fun saveMovieDetails(movieDetailsFromNet: MovieDetails) {

        val detailsDB = MovieDetailsDB(
            movieId = movieDetailsFromNet.id,
            title = movieDetailsFromNet.title,
            detailImageUrl = movieDetailsFromNet.detailImageUrl,
            rating = movieDetailsFromNet.rating,
            reviewCount = movieDetailsFromNet.reviewCount,
            ageLimit = movieDetailsFromNet.ageLimit,
            runTime = movieDetailsFromNet.runtime,
            storyLine = movieDetailsFromNet.storyLine
        )
        val actorsDB = movieDetailsFromNet.actors.map { actor ->
            ActorDB(
                id = actor.id,
                name = actor.name,
                imageUrl = actor.imageUrl,
            )
        }
        val genresDB = movieDetailsFromNet.genres.map { genre ->
            GenreDB(
                id = genre.id,
                name = genre.name,
            )
        }

        val pair = movieDetailsFromNet.actors.map { actor ->
            MovieWithActor(
                movieDetailsFromNet.id,
                actorId = actor.id
            )
        }


        db.getMovieDetailsDao().insertMovieDetailsInDB(
            detailsDB,
            actorsDB,
            genresDB,
            pair
        )
    }
}