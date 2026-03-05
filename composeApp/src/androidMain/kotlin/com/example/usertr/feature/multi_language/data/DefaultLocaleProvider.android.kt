package com.example.usertr.feature.multi_language.data

import java.util.Locale

actual fun getDefaultLocale(): String {
    return Locale.getDefault().toString()
}