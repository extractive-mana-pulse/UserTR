package com.example.usertr.data.database

import androidx.room.Room
import com.example.usertr.core.database.AppDatabase
import platform.Foundation.NSHomeDirectory
import androidx.sqlite.driver.bundled.BundledSQLiteDriver


fun provideDatabase(): AppDatabase {

    val dbPath = NSHomeDirectory() + "/app.db"

    return Room.databaseBuilder<AppDatabase>(
        name = dbPath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}