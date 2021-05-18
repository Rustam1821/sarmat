package com.rustamaliiev.sarmatapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.rustamaliiev.sarmatapp.data.AppDatabase
import com.rustamaliiev.sarmatapp.domain.updater.MoviesWorker
import com.rustamaliiev.sarmatapp.utils.appTAG

class SarmatApp : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDbInstance(this)
        createChannel()
        runWorkManager()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.notification_channel_id),
                getString(R.string.new_movie_notification),
                NotificationManager.IMPORTANCE_LOW
            )
            NotificationManagerCompat.from(applicationContext)
                .createNotificationChannel(channel)
        }
    }

    private fun runWorkManager() {
        val worker = MoviesWorker()
//        WorkManager.getInstance(applicationContext).cancelAllWorkByTag(appTAG)
        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork("my_worker", ExistingPeriodicWorkPolicy.REPLACE, worker.moviesTaskRequest)
//        WorkManager.getInstance(applicationContext).enqueue(worker.moviesTaskRequest)
    }
}