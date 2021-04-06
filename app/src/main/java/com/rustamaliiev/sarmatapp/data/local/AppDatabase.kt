package com.rustamaliiev.sarmatapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rustamaliiev.sarmatapp.data.local.dao.MovieDao
import com.rustamaliiev.sarmatapp.data.local.dao.MovieDetailsDao
import com.rustamaliiev.sarmatapp.data.local.entity.ActorDB
import com.rustamaliiev.sarmatapp.data.local.entity.GenreDB
import com.rustamaliiev.sarmatapp.data.local.entity.MovieDB
import com.rustamaliiev.sarmatapp.data.local.entity.MovieDetailsDB

@Database(
    entities = [ActorDB::class, GenreDB::class, MovieDB::class, MovieDetailsDB::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    companion object {
        private const val DATABASE_NAME = "Movies.db"
        private lateinit var db_instance: AppDatabase //TODO: maybe initialize it with null?

        fun getDbInstance(context: Context): AppDatabase {
            if (db_instance == null) {
                db_instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db_instance
        }
    }
}