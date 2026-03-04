package com.example.usertr.feature.home.presentation.post_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.usertr.feature.auth.presentation.retry.RetryScreen
import com.example.usertr.feature.home.presentation.post_detail.PostDetailUiState

@Composable
internal fun ErrorContent(
    innerPadding: PaddingValues,
    uiState: PostDetailUiState.Error,
    onRetry: () -> Unit
) {
    Box(Modifier.padding(innerPadding)) {
        RetryScreen(errorMessage = uiState.message, onRetry = onRetry)
    }
}