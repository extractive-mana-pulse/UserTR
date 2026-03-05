package com.example.usertr.data.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.example.usertr.core.database.AppDatabase
import com.example.usertr.core.network.HttpClientFactory
import com.example.usertr.feature.multi_language.data.createDataStore
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

    single<DataStore<Preferences>> {
        createDataStore(
            producePath = {
                androidContext()
                    .filesDir
                    .resolve("settings.preferences_pb")
                    .absolutePath
            }
        )
    }

    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().postDao() }
}