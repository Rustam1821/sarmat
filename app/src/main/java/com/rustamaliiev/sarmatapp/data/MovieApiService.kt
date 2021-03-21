package com.rustamaliiev.sarmatapp.data

import com.rustamaliiev.sarmatapp.data.responses.ConfigurationResponse
import com.rustamaliiev.sarmatapp.data.responses.GenresResponse
import com.rustamaliiev.sarmatapp.data.responses.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    //    we need it to receive base_url for images
    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key")
        key: String = API_KEY
    ): ConfigurationResponse

    //
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

}

private val API_KEY = "dce382024c4e6c8dd91b68a4bcd6ff3e"
