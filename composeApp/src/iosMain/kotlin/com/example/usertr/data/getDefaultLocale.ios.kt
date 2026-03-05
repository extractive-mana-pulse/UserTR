package com.example.usertr.data

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

actual fun getDefaultLocale(): String {
    return (NSLocale.preferredLanguages.firstOrNull() as? String) ?: "en"
}