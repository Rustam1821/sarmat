package com.rustamaliiev.sarmatapp.domain.updater

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.rustamaliiev.sarmatapp.MainActivity
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class UpdateMoviesWork(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val movieNotification = loadLastNotification()
            movieNotification?.let { showNotification(it) }
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }
    }

    private fun loadLastNotification(): MovieDetails? {
        var movieDetails: MovieDetails?
        runBlocking(Dispatchers.IO) {
            movieDetails = getMovieDetails()
        }
        return movieDetails

    }

    private suspend fun getMovieDetails(): MovieDetails? {
        val db = SarmatApp.db
        val localMovieRepository: MovieRepository = LocalMovieRepository(db)
        val remoteMovieRepository: MovieRepository = MoviesNetworkRepository()
        var newMovies = emptyList<Movie?>()
        withContext(Dispatchers.IO) {
            db.getMovieDao().getSavedGroupNames().forEach { filmGroup ->
                val savedMoviesList = localMovieRepository.loadMovies(filmGroup)
                val updatedMoviesList = remoteMovieRepository.loadMovies(filmGroup)
                newMovies += updatedMoviesList.minus(savedMoviesList)
                localMovieRepository.updateMovies(updatedMoviesList, filmGroup)
            }
        }
        val topRatedNewMovieId = newMovies.sortedByDescending { it?.rating }.getOrNull(0)?.id
        return if (topRatedNewMovieId != null) {
            remoteMovieRepository.loadMovie(topRatedNewMovieId)
        } else null
    }

    private fun buildNotification(movieDetails: MovieDetails): NotificationCompat.Builder {
        val time = System.currentTimeMillis()
        val genres = movieDetails.genres.map { it.name }.sorted().joinToString()
        val intent = Intent(context, MainActivity::class.java)
            .setAction(Intent.ACTION_VIEW)
            .putExtra("id_movie", movieDetails.id)
        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        return NotificationCompat.Builder(
            context.applicationContext,
            context.getString(R.string.notification_channel_id)
        )
            .setContentTitle(movieDetails.title)
            .setContentText(genres)
            .setSmallIcon(R.drawable.movie_icon)
            .setWhen(time)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    private fun showNotification(movieDetails: MovieDetails) {
        val notificationBuilder = buildNotification(movieDetails)
        val notificationManager = NotificationManagerCompat.from(context.applicationContext)
        val urlImage = if (movieDetails.detailImageUrl != null) {
            movieDetails.detailImageUrl
        } else "null"
        if (urlImage != "null") {
            Glide.with(context)
                .asBitmap()
                .load(urlImage)
                .placeholder(R.drawable.ic_no_photography_24)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d(TAG, "notification error glide")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        notificationManager.notify(
                            context.getString(R.string.notification_channel_id),
                            1,
                            notificationBuilder.setStyle(
                                NotificationCompat.BigPictureStyle().bigPicture(resource)
                            ).build()
                        )
                        return true
                    }
                }).into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        Log.d(TAG, "notification completed")
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        } else {
            notificationManager.notify(
                context.getString(R.string.notification_channel_id),
                1,
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle().bigText(movieDetails.storyLine)
                )
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.drawable.ic_precision
                        )
                    )
                    .build()
            )
        }
    }

    companion object {
        private const val TAG = "MoviesWorker"
    }
}

fun getWorkerConstraints(): Constraints = Constraints
    .Builder()
//    .setRequiresCharging(true)
    .setRequiredNetworkType(NetworkType.UNMETERED)
    .build()

fun getConstrainedRequest(): PeriodicWorkRequest =
    PeriodicWorkRequestBuilder<UpdateMoviesWork>(10, TimeUnit.MINUTES)
        .setConstraints(getWorkerConstraints())
        .build()

