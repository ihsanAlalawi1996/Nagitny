package com.example.project.common

import android.app.Application
import io.paperdb.Paper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
