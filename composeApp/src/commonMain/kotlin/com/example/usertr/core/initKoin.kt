package com.example.usertr.core

import com.example.usertr.feature.auth.data.UserLocalDataSource
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
    }.also {
        // Check all bindings are resolved
        it.koin.get<UserLocalDataSource>() // will print exact missing dep
    }
}