package com.rustamaliiev.sarmatapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// here we store all configuration for retrofit
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient().newBuilder()
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApi: MovieApiService = retrofit.create()
}



