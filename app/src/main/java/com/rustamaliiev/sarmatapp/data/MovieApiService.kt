package com.rustamaliiev.sarmatapp.data

import com.rustamaliiev.sarmatapp.data.responses.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    //we need it to receive base_url for images
    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key")
        key: String = API_KEY
    ): ConfigurationResponse

    //to get list of genres with its' IDs
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key")
        key: String = API_KEY
    ): GenresResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        key: String = API_KEY
    ): TopRatedResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")
        movieId: Int,

        @Query("api_key")
        key: String = API_KEY
    ): MovieDetailsResponse

    // to get actors for the movie
    @GET ("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id")
        movieId: Int,

        @Query("api_key")
        key: String = API_KEY
    ): MovieCastResponse


}

val API_KEY = "dce382024c4e6c8dd91b68a4bcd6ff3e"
