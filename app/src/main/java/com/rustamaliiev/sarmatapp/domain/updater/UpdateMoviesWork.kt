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

class UpdateMoviesWork(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            MoviesUpdater().updateMovies()
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