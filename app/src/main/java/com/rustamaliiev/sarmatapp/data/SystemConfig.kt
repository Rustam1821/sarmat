package com.rustamaliiev.sarmatapp.data

import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.ui.SarmatApp

object SystemConfig {
    const val API_KEY = "dce382024c4e6c8dd91b68a4bcd6ff3e"
    const val DEFAULT_SIZE = "original"
    const val CHILD_AGE = 13
    const val ADULT_AGE = 16
}

enum class FilmGroups(val path: String, val description: Int){
    TOP_RATED ("top_rated", R.string.top_rated),
    POPULAR ("popular", R.string.popular),
    UPCOMING ("upcoming", R.string.upcoming),
}