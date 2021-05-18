package com.rustamaliiev.sarmatapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.work.WorkManager
import com.rustamaliiev.sarmatapp.domain.updater.MoviesWorker
import com.rustamaliiev.sarmatapp.domain.updater.getFromIntent
import com.rustamaliiev.sarmatapp.ui.movieDetails.FragmentMoviesDetails
import com.rustamaliiev.sarmatapp.ui.movies.FragmentMoviesList
import com.rustamaliiev.sarmatapp.ui.movies.FragmentMoviesListClickListener
import com.rustamaliiev.sarmatapp.utils.appTAG

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
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, FragmentMoviesDetails.newInstance(movieID))
            .addToBackStack(null)
            .commit()
    }

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        intent?.let { startIntent(it) }
//    }

    private fun startIntent(intent: Intent) {
        val movieId = getFromIntent(intent)
        if (movieId != 0) {
            onMovieCardClicked(movieId)
        }
    }
}