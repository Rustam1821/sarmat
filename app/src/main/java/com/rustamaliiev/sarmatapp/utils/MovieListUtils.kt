package com.rustamaliiev.sarmatapp.utils

import com.rustamaliiev.sarmatapp.ItemMovie
import com.rustamaliiev.sarmatapp.R

fun movieListFilling(list: MutableList<ItemMovie>) {
    list.run {
        add(
            ItemMovie(
                "Avengers: End Game",
                "Action, Adventure, Drama",
                125, 13, 137,
                R.drawable.avengers_poster_color, 3.5f
            )
        )
        add(
            ItemMovie(
                "Tenet",
                "Action, Sci-Fi, Thriller",
                98, 16, 97,
                R.drawable.tenet_poster_color, 4.8f
            )
        )
        add(
            ItemMovie(
                "Black Widow",
                "Action, Adventure, Sci-Fi",
                38, 13, 102,
                R.drawable.black_widow_poster_color, 3.5f
            )
        )
        add(
            ItemMovie(
                "Wonder Woman 1984",
                "Action, Adventure, Fantasy",
                74, 13, 120,
                R.drawable.wonder_woman_poster_color, 2.3f
            )
        )
        add(
            ItemMovie(
                "Amelie",
                "Magical realism",
                92, 13, 129,
                R.drawable.amelie_poster_color, 5f
            )
        )
        add(
            ItemMovie(
                "Dumb and Dumber",
                "Comedy",
                92, 13, 106,
                R.drawable.dumb_and_dumber_poster_color, 5f
            )
        )
    }
}