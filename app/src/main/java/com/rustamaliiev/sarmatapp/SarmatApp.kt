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
import com.rustamaliiev.sarmatapp.utils.NOTIFICATION_CHANNEL_ID
import com.rustamaliiev.sarmatapp.utils.NOTIFICATION_NAME

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
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            NotificationManagerCompat.from(applicationContext)
                .createNotificationChannel(channel)
        }
    }

    private fun runWorkManager() {
        val worker = MoviesWorker()
        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "my_worker",
                ExistingPeriodicWorkPolicy.KEEP,
                worker.moviesTaskRequest
            )
    }
}