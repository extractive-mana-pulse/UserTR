package com.example.usertr.feature.auth.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

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

    // Loading UI while deciding
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}