package com.rustamaliiev.sarmatapp

import android.app.Application
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