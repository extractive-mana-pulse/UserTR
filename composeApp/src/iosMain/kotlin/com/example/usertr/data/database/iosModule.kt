package com.example.usertr.data.database

import com.example.usertr.core.database.AppDatabase
import org.koin.dsl.module

val iosModule = module {
    single<AppDatabase> { provideDatabase() }
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().postDao() }
}