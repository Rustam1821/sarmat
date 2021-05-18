package com.rustamaliiev.sarmatapp.domain.updater

import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import com.rustamaliiev.sarmatapp.utils.appTAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MoviesWorker {

    private val TAG = appTAG
    private val repeatInterval = 15L
    private val delay = 10L
    private val networkType = NetworkType.CONNECTED
    private val constraint = Constraints.Builder()
        .setRequiredNetworkType(networkType)
        .build()

    val moviesTaskRequest =
        PeriodicWorkRequest.Builder(UpdateMoviesWork::class.java, repeatInterval, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .setInitialDelay(delay, TimeUnit.SECONDS)
            .addTag(TAG)
            .build()
}