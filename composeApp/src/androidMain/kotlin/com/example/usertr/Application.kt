package com.example.usertr

import android.app.Application
import com.example.usertr.core.di.appModule
import com.example.usertr.data.database.androidModule
import com.example.usertr.feature.auth.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(androidModule, appModule, authModule)
        }
    }
}