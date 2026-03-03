package com.example.usertr.data.database

import androidx.room.Room
import com.example.usertr.core.database.AppDatabase
import com.example.usertr.core.network.HttpClientFactory
import io.ktor.client.plugins.logging.LoggingFormat
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app.db"
        ).build()
    }
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().postDao() }
}