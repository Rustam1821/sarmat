package com.rustamaliiev.sarmatapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Gravity.apply
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.transition.*
import com.rustamaliiev.sarmatapp.databinding.ActivityMainBinding
import com.rustamaliiev.sarmatapp.domain.updater.getFromIntent
import com.rustamaliiev.sarmatapp.ui.movieDetails.FragmentMoviesDetails
import com.rustamaliiev.sarmatapp.ui.movies.FragmentMoviesList
import com.rustamaliiev.sarmatapp.ui.movies.FragmentMoviesListClickListener

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                .add(R.id.main_container, FragmentMoviesList.newInstance())
                .add(binding.mainContainer.id, FragmentMoviesList.newInstance()) //TODO 01: is it ok?
                .commit()
            intent?.let(::startIntent)
        }
    }

    override fun onMovieCardClicked(movieID: Int) {
        val movieDetailsFragment = FragmentMoviesDetails.newInstance(movieID)
        setSmoothShifting(movieDetailsFragment)
            supportFragmentManager.beginTransaction()
    //            .add(R.id.main_container, movieDetailsFragment)
                .add(binding.mainContainer.id, movieDetailsFragment) //TODO 02: is it ok?
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