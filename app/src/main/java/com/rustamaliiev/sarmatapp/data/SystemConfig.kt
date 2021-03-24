package com.rustamaliiev.sarmatapp.data

object SystemConfig {
    const val API_KEY = "dce382024c4e6c8dd91b68a4bcd6ff3e"
    const val DEFAULT_SIZE = "original"
}

enum class FilmGroups(val path: String, val description: String){
    TOP_RATED ("top_rated", "Top rated"),
    POPULAR ("popular", "Popular"),
    UPCOMING ("upcoming", "Upcoming"),
}