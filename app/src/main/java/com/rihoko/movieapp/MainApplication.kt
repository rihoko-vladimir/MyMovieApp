package com.rihoko.movieapp

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    override fun onCreate() {
        appContext = this.applicationContext
        super.onCreate()
    }
    companion object{
        lateinit var appContext : Context
    }
}