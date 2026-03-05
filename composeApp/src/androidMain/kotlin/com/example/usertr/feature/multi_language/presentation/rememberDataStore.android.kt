package com.example.usertr.feature.multi_language.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.usertr.data.createDataStore

@Composable
actual fun rememberDataStore(): DataStore<Preferences> {
    val context = LocalContext.current
    return remember {
        createDataStore(context.applicationContext)
    }
}