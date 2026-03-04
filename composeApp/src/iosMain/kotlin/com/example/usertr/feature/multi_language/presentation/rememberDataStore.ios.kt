package com.example.usertr.feature.multi_language.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.usertr.feature.multi_language.data.createDataStore

@Composable
actual fun rememberDataStore(): DataStore<Preferences> {
    return remember { createDataStore() }
}