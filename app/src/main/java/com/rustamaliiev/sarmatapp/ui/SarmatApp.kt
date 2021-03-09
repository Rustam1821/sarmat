package com.rustamaliiev.sarmatapp.ui

import android.app.Application
import android.content.Context

class SarmatApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
            private set
    }
}