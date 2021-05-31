package com.rustamaliiev.sarmatapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Gravity.apply
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import com.rustamaliiev.sarmatapp.domain.updater.getFromIntent
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
            intent?.let(::startIntent)
        }
    }

    override fun onMovieCardClicked(movieID: Int) {
        val movieDetailsFragment = FragmentMoviesDetails.newInstance(movieID)
        setSmoothShifting(movieDetailsFragment)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, movieDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setSmoothShifting(movieDetailsFragment: FragmentMoviesDetails) {
        val transitionSet = TransitionSet().apply {
            addTransition(Fade().apply { duration = 500 })
            addTransition(Slide().apply {
                slideEdge = Gravity.LEFT
                duration = 600
            })
        }
        with(movieDetailsFragment) {
            enterTransition = transitionSet
            exitTransition = transitionSet
        }
    }


    private fun startIntent(intent: Intent) {
        val movieId = getFromIntent(intent)
        if (movieId != 0) {
            onMovieCardClicked(movieId)
        }
    }
}