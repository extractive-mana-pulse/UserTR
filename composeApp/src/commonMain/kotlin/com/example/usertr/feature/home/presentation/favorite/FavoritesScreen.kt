package com.example.usertr.feature.home.presentation.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usertr.core.components.Loading
import com.example.usertr.core.components.UserTrTopBar
import com.example.usertr.feature.home.presentation.favorite.components.EmptyFavoriteScreen
import com.example.usertr.feature.home.presentation.post_list.Post
import com.example.usertr.feature.home.presentation.post_list.components.PostItem
import org.jetbrains.compose.resources.stringResource
import usertr.composeapp.generated.resources.Res
import usertr.composeapp.generated.resources.favorite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    uiState: FavoritesUiState,
    onPostClick: (Post) -> Unit,
    onBack: () -> Unit,
    onFavoriteToggle: (Post) -> Unit,
) {
    Scaffold(
        topBar = {
            UserTrTopBar(
                onBack = onBack,
                title = stringResource(Res.string.favorite),
                icon = Icons.AutoMirrored.Outlined.ArrowBack
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is FavoritesUiState.Loading -> Loading(innerPadding)

            is FavoritesUiState.Empty -> EmptyFavoriteScreen(innerPadding)

            is FavoritesUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(uiState.posts, key = { it.id }) { post ->
                        PostItem(
                            post = post,
                            isFavorite = true,
                            onFavoriteToggle = {
                                onFavoriteToggle(post)
                            },
                            onClick = { onPostClick(post) }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}