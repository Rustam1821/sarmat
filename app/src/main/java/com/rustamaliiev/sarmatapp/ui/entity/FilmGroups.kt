package com.rustamaliiev.sarmatapp.ui.entity

import com.rustamaliiev.sarmatapp.R

enum class FilmGroups(val path: String, val description: Int) {
    TOP_RATED("top_rated", R.string.top_rated),
    POPULAR("popular", R.string.popular),
    UPCOMING("upcoming", R.string.upcoming),
}