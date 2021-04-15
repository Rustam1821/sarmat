package com.rustamaliiev.sarmatapp.domain.repository

import android.util.Log
import com.rustamaliiev.sarmatapp.data.AppDatabase
import com.rustamaliiev.sarmatapp.domain.entity.Actor
import com.rustamaliiev.sarmatapp.domain.entity.Genre
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails

class LocalMovieRepository(private val db: AppDatabase) : MovieRepository {

    override suspend fun loadMovies(selector: String): List<Movie> =
        db.getMovieDao().getMovies(selector).map { movieDB ->

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

    override suspend fun loadMovie(movieId: Int): MovieDetails =
        with(db.getMovieDetailsDao().getMovieDetails(movieId)) {
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

    override suspend fun saveMovies(moviesFromNet: List<Movie>, filmGroup: String) {
        Log.i("QQQ", "Saving ${moviesFromNet.size} movies")
        db.getMovieDao().insertMoviesInDb(moviesFromNet, filmGroup)
    }

    override suspend fun updateMovies(movies: List<Movie>, movieGroup: String) {
        Log.i("UPD", "Updating ${movies.size} movies for the group $movieGroup")
        db.getMovieDao().updateMoviesInDb(movies, movieGroup)
    }

    override suspend fun saveMovieDetails(movieDetailsFromNet: MovieDetails) {
        Log.i("QQQ", "Saving ${movieDetailsFromNet.title} with actors= ${movieDetailsFromNet.actors.size} and genres: ${movieDetailsFromNet.genres.map{it.name}.joinToString (", ")}")
        db.getMovieDetailsDao().insertMovieDetailsInDB(movieDetailsFromNet)
    }
}