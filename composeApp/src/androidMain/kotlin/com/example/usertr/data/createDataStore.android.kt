package com.example.usertr.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.usertr.feature.multi_language.data.createDataStore

fun createDataStore(context: Context): DataStore<Preferences> {
    return createDataStore(
        producePath = {
            context.filesDir.resolve("prefs.preferences_pb").absolutePath
        }
    )
}