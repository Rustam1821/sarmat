package com.rustamaliiev.sarmatapp.domain.updater

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.rustamaliiev.sarmatapp.utils.appTAG
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