package com.example.usertr.feature.home.presentation.post_detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.usertr.feature.home.presentation.post_detail.components.ErrorContent
import com.example.usertr.core.components.Loading
import com.example.usertr.core.components.UserTrTopBar
import com.example.usertr.feature.home.presentation.post_detail.components.PostDetailContent
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    uiState: PostDetailUiState,
    onRetry: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            UserTrTopBar(
                onBack = onBack,
                title = stringResource(Res.string.post_detail),
                icon = Icons.AutoMirrored.Outlined.ArrowBack
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is PostDetailUiState.Loading -> Loading(innerPadding)

            is PostDetailUiState.Error -> ErrorContent(innerPadding, uiState, onRetry)

            is PostDetailUiState.Success -> PostDetailContent(innerPadding, uiState)
        }
    }
}