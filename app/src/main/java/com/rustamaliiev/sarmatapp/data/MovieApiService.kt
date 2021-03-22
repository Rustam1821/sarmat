package com.rustamaliiev.sarmatapp.data

import com.rustamaliiev.sarmatapp.data.responses.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    //we need it to receive base_url for images
    @GET("configuration")
    suspend fun getConfiguration(
    ): ConfigurationResponse

    //to get list of genres with its' IDs
    @GET("genre/movie/list")
    suspend fun getGenres(
    ): GenresResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
    ): TopRatedResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")
        movieId: Int,

    ): MovieDetailsResponse

    // to get actors for the movie
    @GET ("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id")
        movieId: Int,
    ): MovieCastResponse
}
