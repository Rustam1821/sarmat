package com.rustamaliiev.sarmatapp.data

import com.rustamaliiev.sarmatapp.data.SystemConfig.DEFAULT_SIZE
import com.rustamaliiev.sarmatapp.data.responses.ImageResponse
import com.rustamaliiev.sarmatapp.model.Actor
import com.rustamaliiev.sarmatapp.model.Genre
import com.rustamaliiev.sarmatapp.model.Movie
import com.rustamaliiev.sarmatapp.model.MovieDetails
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// here we store all configurations for retrofit
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private var imageResponse: ImageResponse? = null
    private var baseUrl: String? = null

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(ApiKeyInterceptor())
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApi: MovieApiService = retrofit.create()

    suspend fun loadMovies(selector: String): List<Movie> {
        getConfigurations()
        val genres = movieApi.getGenres().genres
        return movieApi.getMoviesList(selector).results.map { movieResponse ->
            Movie(
                id = movieResponse.id,
                title = movieResponse.title,
                imageUrl = concatenateUrl(baseUrl, movieResponse.posterPicture),
                rating = movieResponse.voteAverage / 2,
                reviewCount = movieResponse.votesCount,
                ageLimit = if (movieResponse.adult) 16 else 13,

                // where can I get running time? only in movie details
                runningTime = 999,

                genres = genres.filter { genreResponse ->
                    movieResponse.genreIds.contains(genreResponse.id)
                }.map { genre ->
                    Genre(genre.id, genre.name)
                },

                isLiked = false
            )
        }
    }

    suspend fun loadMovie(movieId: Int): MovieDetails {
        getConfigurations()
        val movieDetails = movieApi.getMovieDetails(movieId)
        return MovieDetails(
            id = movieDetails.id,
            title = movieDetails.title,
            storyLine = movieDetails.overview.orEmpty(),
            detailImageUrl = concatenateUrl(baseUrl, movieDetails.backdropPath),
            rating = movieDetails.voteAverage / 2,
            reviewCount = movieDetails.voteCount ?: 0,
            ageLimit = if (movieDetails.isAdult) 16 else 13,
            runtime = movieDetails.runtime ?: 0,
            genres = movieDetails.genres.map { Genre(it.id, it.name) },
            actors = movieApi.getCast(movieId).casts.map { castResponse ->
                Actor(
                    id = castResponse.id,
                    name = castResponse.name,
                    imageUrl = concatenateUrl(baseUrl, castResponse.profilePath)
                )
            }
        )
    }

    private suspend fun getConfigurations() {
        if (imageResponse == null) {
            imageResponse = movieApi.getConfiguration().images
            baseUrl = imageResponse?.baseUrl
        }
    }

    private fun concatenateUrl(url: String?, path: String?): String? {
        return if (url == null || path == null) {
            null
        } else (url.plus(DEFAULT_SIZE).plus(path))
    }
}



