package com.example.usertr.feature.auth.presentation.splash

sealed interface SplashDestination {
    data object Loading : SplashDestination
    data object UserList : SplashDestination
    data object Retry : SplashDestination
}
