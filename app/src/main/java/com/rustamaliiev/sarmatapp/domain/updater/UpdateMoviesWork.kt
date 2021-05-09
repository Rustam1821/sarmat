package com.rustamaliiev.sarmatapp.domain.updater

import android.content.Context
import android.util.Log
import androidx.work.*
import com.rustamaliiev.sarmatapp.data.entity.MovieDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class UpdateMoviesWork(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    private val notifications = Notifications(context)
    private val moviesUpdater: MoviesUpdater = MoviesUpdater()

    override suspend fun doWork(): Result {
        return try {
            val newMovie = moviesUpdater.getNewMovieForNotification()
            if (newMovie != null) {
                notifications.initialize()
                notifications.showNotification(newMovie)
            }

            moviesUpdater.updateMovies()
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }
    }
}

fun getWorkerConstraints(): Constraints = Constraints
    .Builder()
    .setRequiresCharging(true)
    .setRequiredNetworkType(NetworkType.UNMETERED)
    .build()

fun getConstrainedRequest(): PeriodicWorkRequest =
    PeriodicWorkRequestBuilder<UpdateMoviesWork>(15, TimeUnit.HOURS)
        .setConstraints(getWorkerConstraints())
        .build()