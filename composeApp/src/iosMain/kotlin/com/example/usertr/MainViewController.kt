package com.example.usertr

import androidx.compose.ui.window.ComposeUIViewController
import com.example.usertr.core.di.appModule
import com.example.usertr.core.initKoin
import com.example.usertr.data.database.iosModule
import com.example.usertr.feature.auth.di.authModule

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {
            modules(iosModule, appModule, authModule)
        }
    }
) {
    App()
}