package com.rustamaliiev.sarmatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rustamaliiev.sarmatapp.ui.movieDetails.FragmentMoviesDetails
import com.rustamaliiev.sarmatapp.ui.movies.FragmentMoviesList
import com.rustamaliiev.sarmatapp.ui.movies.FragmentMoviesListClickListener

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList.newInstance())
                .commit()
        }
    }

    override fun onMovieCardClicked(movieID: Int) {

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, FragmentMoviesDetails.newInstance(movieID))
            .addToBackStack(null)
            .commit()
    }
}