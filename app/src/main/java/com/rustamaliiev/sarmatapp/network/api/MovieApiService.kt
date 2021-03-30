package com.rustamaliiev.sarmatapp.network.api

import com.rustamaliiev.sarmatapp.network.entity.*
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    //we need it to receive base_url for images
    @GET("configuration")
    suspend fun getConfiguration(
    ): ApiConfigurationResponse

    //to get list of genres with its' IDs
    @GET("genre/movie/list")
    suspend fun getGenres(
    ): ApiGenresResponse

    @GET("movie/{selector}")
    suspend fun getMoviesList(
        @Path("selector")
        selector: String
    ): ApiMovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")
        movieId: Int,

    ): ApiMovieDetailsResponse

    // to get actors for the movie
    @GET ("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id")
        movieId: Int,
    ): ApiMovieCastResponse
}

