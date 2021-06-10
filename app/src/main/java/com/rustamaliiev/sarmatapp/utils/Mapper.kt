package com.rustamaliiev.sarmatapp.utils

import com.rustamaliiev.sarmatapp.data.entity.*
import com.rustamaliiev.sarmatapp.domain.entities.Actor
import com.rustamaliiev.sarmatapp.domain.entities.Genre
import com.rustamaliiev.sarmatapp.domain.entities.Movie
import com.rustamaliiev.sarmatapp.domain.entities.MovieDetails
import com.rustamaliiev.sarmatapp.network.config.SystemConfig

fun mapMovieDomainToDB(movies: List<Movie>, filmGroup: String): List<MovieDB> {
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

fun mapGenreDomainToDB(movies: List<Movie>): List<GenreDB> {
    return movies.flatMap { movie ->
        movie.genres.map { genre ->
            GenreDB(
                id = genre.id,
                name = genre.name
            )
        }
    }
}

fun mapToMovieGenrePairs(movies: List<Movie>): List<MovieWithGenre> {
    return movies.flatMap { movie ->
        movie.genres.map { genre ->
            MovieWithGenre(
                movieId = movie.id,
                genreId = genre.id
            )
        }
    }
}

fun mapMovieGenrePairToMovie(pair: MovieGenrePair): Movie =
    Movie(
        id = pair.movie.id,
        title = pair.movie.title,
        imageUrl = pair.movie.imageUrl,
        rating = pair.movie.rating,
        reviewCount = pair.movie.reviewCount,
        ageLimit = pair.movie.ageLimit,
        runningTime = pair.movie.runningTime,
        genres = pair.genres.map { genreDB ->
            Genre(genreDB.id, genreDB.name)
        },
        isLiked = pair.movie.isLiked
    )

fun mapMovieDetailsActorGenrePairToMovieDetails(pair: MovieDetailsActorGenrePair): MovieDetails =
    with(pair) {
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

fun mapMovieDetailsToMovieDetailsBD(md: MovieDetails): MovieDetailsDB =
    MovieDetailsDB(
        movieId = md.id,
        title = md.title,
        detailImageUrl = md.detailImageUrl,
        rating = md.rating,
        reviewCount = md.reviewCount,
        ageLimit = md.ageLimit,
        runTime = md.runtime,
        storyLine = md.storyLine,
    )

private fun buildImageUrl(url: String, path: String?): String? {
    return path?.let {
        "$url${SystemConfig.DEFAULT_IMAGE_SIZE}$path"
    }
}



