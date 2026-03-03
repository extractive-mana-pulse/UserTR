package com.example.usertr.data.database

import android.content.Context
import androidx.room.Room
import com.example.usertr.core.database.AppDatabase
import kotlinx.coroutines.Dispatchers


fun initDatabase(context: Context): AppDatabase {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("app.db")
    return Room
        .databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}