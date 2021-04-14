package com.rustamaliiev.sarmatapp.domain.updater

import android.content.Context
import android.util.Log
import androidx.work.*
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class UpdateMoviesWork(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {
    override fun doWork(): Result {
        return try {
            Log.e("worker", "i've turned on at ${LocalDateTime.now()}")
            Result.success()
        } catch (error: Throwable){
            Result.failure()
        }
    }
}

fun getWorkerConstraints(): Constraints = Constraints
    .Builder()
    .setRequiresCharging(true)
    .setRequiredNetworkType(NetworkType.UNMETERED)
    .build()

fun getConstrainedRequest(): PeriodicWorkRequest = PeriodicWorkRequestBuilder<UpdateMoviesWork>(2, TimeUnit.HOURS)
    .setConstraints(getWorkerConstraints())
    .build()