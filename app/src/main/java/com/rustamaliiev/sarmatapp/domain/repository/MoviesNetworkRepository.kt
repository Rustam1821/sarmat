package com.rustamaliiev.sarmatapp.domain.repository

import com.rustamaliiev.sarmatapp.domain.entities.Actor
import com.rustamaliiev.sarmatapp.domain.entities.Genre
import com.rustamaliiev.sarmatapp.domain.entities.Movie
import com.rustamaliiev.sarmatapp.domain.entities.MovieDetails
import com.rustamaliiev.sarmatapp.network.config.NetworkModule
import com.rustamaliiev.sarmatapp.network.config.SystemConfig

class MoviesNetworkRepository : BaseMovieRepository {
    private lateinit var imagesBaseUrl: String
    private var isConfigReceived = false

    override suspend fun loadMovies(selector: String): List<Movie> {
        getConfigurations()
        val genres = NetworkModule.movieApi.getGenres().genres
        return NetworkModule.movieApi.getMoviesList(selector).results.map { movieResponse ->
            Movie(
                id = movieResponse.id,
                title = movieResponse.title,
                imageUrl = buildImageUrl(
                    imagesBaseUrl,
                    movieResponse.posterPicture
                ),
                rating = movieResponse.voteAverage / 2,
                reviewCount = movieResponse.votesCount,
                ageLimit = if (movieResponse.adult) SystemConfig.ADULT_AGE else SystemConfig.CHILD_AGE,

                runningTime = 999,

                genres = genres.filter { genreResponse ->
                    movieResponse.genreIds.contains(genreResponse.id)
                }.map { genre ->
                    Genre(genre.id, genre.name)
                },
                isLiked = false
            )
        }.sortedByDescending { movie ->
            movie.rating
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        getConfigurations()
        val movieDetails = NetworkModule.movieApi.getMovieDetails(movieId)
        return MovieDetails(
            id = movieDetails.id,
            title = movieDetails.title,
            storyLine = movieDetails.overview.orEmpty(),
            detailImageUrl = buildImageUrl(
                imagesBaseUrl,
                movieDetails.backdropPath
            ),
            rating = movieDetails.voteAverage / 2,
            reviewCount = movieDetails.voteCount ?: 0,
            ageLimit = if (movieDetails.isAdult) SystemConfig.ADULT_AGE else SystemConfig.CHILD_AGE,
            runtime = movieDetails.runtime ?: 0,
            genres = movieDetails.apiGenres.map { Genre(it.id, it.name) },
            actors = NetworkModule.movieApi.getCast(movieId).casts.map { castResponse ->
                Actor(
                    id = castResponse.id,
                    name = castResponse.name,
                    imageUrl = buildImageUrl(imagesBaseUrl, castResponse.profilePath)
                )
            }
        )
    }

    private suspend fun getConfigurations() {
        if (!isConfigReceived) {
            imagesBaseUrl = NetworkModule.movieApi.getConfiguration().images.baseUrl
            isConfigReceived = true
        }
    }

    private fun buildImageUrl(url: String, path: String?): String? {
        return path?.let {
            "$url${SystemConfig.DEFAULT_IMAGE_SIZE}$path"
        }
    }
}
