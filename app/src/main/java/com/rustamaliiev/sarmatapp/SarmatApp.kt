package com.rustamaliiev.sarmatapp

import android.app.Application
import android.content.Context
import android.util.Log
import com.rustamaliiev.sarmatapp.data.AppDatabase

class SarmatApp : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDbInstance(this)

    }
}