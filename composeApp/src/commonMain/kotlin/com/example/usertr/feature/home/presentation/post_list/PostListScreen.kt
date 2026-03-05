package com.example.usertr.feature.home.presentation.post_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.WifiOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usertr.core.components.UserTrTopBar
import com.example.usertr.feature.auth.presentation.retry.RetryScreen
import com.example.usertr.feature.home.presentation.post_list.components.PostItem
import org.jetbrains.compose.resources.stringResource
import usertr.composeapp.generated.resources.Res
import usertr.composeapp.generated.resources.posts
import usertr.composeapp.generated.resources.showing_cached_data

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(
    uiState: PostListUiState,
    onPostClick: (Post) -> Unit,
    onFavoriteToggle: (Post) -> Unit,
    onRetry: () -> Unit,
    onRefresh: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            UserTrTopBar(
                onBack = onBack,
                title = stringResource(Res.string.posts),
                icon = Icons.AutoMirrored.Outlined.ArrowBack
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is PostListUiState.Loading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }

            is PostListUiState.Error -> {
                PullToRefreshBox(
                    isRefreshing = false,
                    onRefresh = onRetry,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    RetryScreen(errorMessage = uiState.message, onRetry = onRetry)
                }
            }

            is PostListUiState.Success -> {
                PullToRefreshBox(
                    isRefreshing = false,
                    onRefresh = onRefresh,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        if (uiState.isOffline) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.WifiOff,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = stringResource(Res.string.showing_cached_data),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            HorizontalDivider()
                        }

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 8.dp)
                        ) {
                            items(uiState.posts, key = { it.id }) { post ->
                                PostItem(
                                    post = post,
                                    isFavorite = post.id in uiState.favoriteIds,
                                    onFavoriteToggle = { onFavoriteToggle(post) },
                                    onClick = { onPostClick(post) }
                                )
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}