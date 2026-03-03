package com.example.usertr.core.network

fun constructUrl(url: String, baseUrl: String = com.example.usertr.baseUrl): String {
    return when {
        url.contains(baseUrl) -> url
        else -> {
            val normalizedBase = baseUrl.removeSuffix("/")
            val normalizedUrl = url.removePrefix("/")
            "$normalizedBase/$normalizedUrl"
        }
    }
}