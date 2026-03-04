package com.example.usertr.feature.multi_language.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.usertr.feature.multi_language.data.getDefaultLocale
import platform.Foundation.NSUserDefaults

actual object LocalAppLocale {

    private val defaultLocale = getDefaultLocale()

    private val LocalAppLocale = staticCompositionLocalOf { defaultLocale }
    actual val current: String
        @Composable
        get() = LocalAppLocale.current

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val newLocale = value ?: defaultLocale
        if(value == null) {
            NSUserDefaults.Companion.standardUserDefaults.removeObjectForKey("AppleLanguages")
        } else {
            NSUserDefaults.Companion.standardUserDefaults.setObject(
                listOf(newLocale),
                "AppleLanguages"
            )
        }

        return LocalAppLocale provides newLocale
    }
}