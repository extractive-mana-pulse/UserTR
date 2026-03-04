package com.example.usertr.feature.auth.presentation.splash

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import com.example.usertr.core.components.Loading

@Composable
fun SplashScreen(
    destination: SplashDestination,
    onNavigateToUserList: () -> Unit,
    onNavigateToRetry: () -> Unit
) {
    LaunchedEffect(destination) {
        when (destination) {
            SplashDestination.UserList -> onNavigateToUserList()
            SplashDestination.Retry -> onNavigateToRetry()
            SplashDestination.Loading -> Unit
        }
    }
    Loading(innerPadding = PaddingValues(0.dp))
}