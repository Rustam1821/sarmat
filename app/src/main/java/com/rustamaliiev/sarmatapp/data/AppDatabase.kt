package com.rustamaliiev.sarmatapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rustamaliiev.sarmatapp.data.dao.MovieDao
import com.rustamaliiev.sarmatapp.data.dao.MovieDetailsDao
import com.rustamaliiev.sarmatapp.data.entity.*

@Database(
    entities = [ActorDB::class, GenreDB::class,
        MovieDB::class, MovieDetailsDB::class,
        MovieWithGenre::class], version = 8
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    companion object {
        private const val DATABASE_NAME = "Movies.db"
        private var db_instance: AppDatabase? = null

        fun getDbInstance(context: Context): AppDatabase {
            return db_instance ?: let {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}