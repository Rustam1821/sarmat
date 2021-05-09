package com.rustamaliiev.sarmatapp.domain.updater

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_HIGH
import androidx.core.net.toUri
import com.rustamaliiev.sarmatapp.MainActivity
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails

class Notifications(private val context: Context) {
    companion object {
        private const val CHANNEL_NEW_MOVIE = "new_movie"
        private const val REQUEST_CONTENT = 1
        private const val MOVIE_TAG = "movie"
    }

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    fun initialize() {
        if (notificationManagerCompat.getNotificationChannel(CHANNEL_NEW_MOVIE) == null) {
            notificationManagerCompat.createNotificationChannel(
                NotificationChannelCompat.Builder(CHANNEL_NEW_MOVIE, IMPORTANCE_HIGH)
                    .setName(context.getString(R.string.new_movie_notification))
                    .setDescription(context.getString(R.string.channel_new_movie_description))
                    .build()
            )
        }
    }


    fun showNotification(movie: Movie) {
        val contentUri = "https://sarmatapp.rustamaliiev.com/movie/${movie.id}".toUri()
        val builder = NotificationCompat.Builder(context, CHANNEL_NEW_MOVIE)
            .setContentTitle(movie.title)
            .setContentText(movie.genres.joinToString (", "))
            .setSmallIcon(R.drawable.movie_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOnlyAlertOnce(true)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    REQUEST_CONTENT,
                    Intent(context, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
        notificationManagerCompat.notify(MOVIE_TAG, movie.id, builder.build())
    }

    fun dismissNotification(movieId: Int) {
        notificationManagerCompat.cancel(MOVIE_TAG, movieId)
    }
}