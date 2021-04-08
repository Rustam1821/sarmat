package com.rustamaliiev.sarmatapp.domain.repository

import android.util.Log
import com.rustamaliiev.sarmatapp.data.AppDatabase
import com.rustamaliiev.sarmatapp.data.entity.MovieDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDetailsDB
import com.rustamaliiev.sarmatapp.domain.entity.Actor
import com.rustamaliiev.sarmatapp.domain.entity.Genre
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails

class LocalMovieRepository(private val db: AppDatabase) : MovieRepository {

    override suspend fun loadMovies(selector: String): List<Movie> =
        db.getMovieDao().getMovies(selector).map { movieDB ->
            Log.e("QQQ", "LocalMovieRepository, loadMovies")
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

    suspend fun clean(){
        db.getMovieDao().clearLocalTable()
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails =
        with(db.getMovieDetailsDao().getMovieDetails()) {
            MovieDetails(
                id = details.parentId,
                title = details.title,
                storyLine = details.overview ?: "",
                detailImageUrl = details.backdropPath,
                rating = details.rating,
                reviewCount = details.reviewCount,
                ageLimit = details.ageLimit,
                runtime = details.runTime ?: 0,
                genres = genres.map { genreDB ->
                    Genre(genreDB.id, genreDB.name)
                },
                actors = actors.map { actorDB ->
                    Actor(actorDB.id, actorDB.name, actorDB.imageUrl)
                }

            )
        }

    suspend fun saveMovies(moviesFromNet: List<Movie>, filmGroups: String) {
        val movies = moviesFromNet.map { movie ->
            MovieDB(
                id = movie.id,
                title = movie.title,
                imageUrl = movie.imageUrl,
                rating = movie.rating,
                reviewCount = movie.reviewCount,
                ageLimit = movie.ageLimit,
                runningTime = movie.runningTime,
                isLiked = movie.isLiked,
                filmGroups = filmGroups
            )
        }
        db.getMovieDao().insertMovies(movies)
    }

    suspend fun saveMovieDetails(movieDetailsFromNet: MovieDetails) {

        val detailsDB = MovieDetailsDB(
            parentId = movieDetailsFromNet.id,
            title = movieDetailsFromNet.title,
            backdropPath = movieDetailsFromNet.detailImageUrl,
            rating = movieDetailsFromNet.rating,
            reviewCount = movieDetailsFromNet.reviewCount,
            ageLimit = movieDetailsFromNet.ageLimit,
            runTime = movieDetailsFromNet.runtime,
            overview = movieDetailsFromNet.storyLine
        )
        db.getMovieDetailsDao().insertMovieDetails(detailsDB)
    }
}