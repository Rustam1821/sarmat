package com.rustamaliiev.sarmatapp.utils

import com.rustamaliiev.sarmatapp.data.entity.GenreDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDB
import com.rustamaliiev.sarmatapp.data.entity.MovieWithGenre
import com.rustamaliiev.sarmatapp.domain.entity.Movie

fun getMovieDBs(movies: List<Movie>, filmGroup: String): List<MovieDB> {
    return movies.map { movie ->
        MovieDB(
            id = movie.id,
            title = movie.title,
            imageUrl = movie.imageUrl,
            rating = movie.rating,
            reviewCount = movie.reviewCount,
            ageLimit = movie.ageLimit,
            runningTime = movie.runningTime,
            isLiked = movie.isLiked,
            filmGroups = filmGroup
        )
    }
}

fun getGenresDBs(movies: List<Movie>, filmGroup: String): List<GenreDB> {
    return movies.flatMap { movie ->
        movie.genres.map { genre ->
            GenreDB(
                id = genre.id,
                name = genre.name
            )
        }
    }
}

fun getMovieGenrePairs(movies: List<Movie>, filmGroup: String): List<MovieWithGenre> {
    return movies.flatMap { movie ->
        movie.genres.map { genre ->
            MovieWithGenre(
                movieId = movie.id,
                genreId = genre.id
            )
        }
    }
}