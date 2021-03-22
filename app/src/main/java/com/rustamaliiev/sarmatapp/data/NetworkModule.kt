package com.rustamaliiev.sarmatapp.data

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

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(ApiKeyInterceptor())
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApi: MovieApiService = retrofit.create()

    suspend fun loadMovies(): List<Movie> {
        val genres = movieApi.getGenres().genres
        return movieApi.getTopRatedMovies().results.map { movieResponse ->
            Movie(
                id = movieResponse.id,
                title = movieResponse.title,
                imageUrl = movieResponse.posterPicture,
                rating = movieResponse.voteAverage,
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
        val movieDatails = movieApi.getMovieDetails(movieId)
        return MovieDetails(
            id = movieDatails.id,
            title = movieDatails.title,
            storyLine = movieDatails.overview.orEmpty(),
            detailImageUrl = movieDatails.backdropPath,
            rating = movieDatails.popularity,
            reviewCount = movieDatails.revenue,
            ageLimit = if (movieDatails.isAdult) 16 else 13,
            runningTime = movieDatails.runtime?:0


        )
    }
}
//                actors = movieApi.getCast().casts.filter { castResponse ->
//                    movieResponse.actors.contains(castResponse.id)
//                }.map { actor->
//                    Actor(actor.id, actor.name, actor.profilePath)
//                }



