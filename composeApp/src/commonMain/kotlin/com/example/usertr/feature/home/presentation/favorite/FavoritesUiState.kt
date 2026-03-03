package com.example.usertr.feature.home.presentation.favorite

import com.example.usertr.feature.home.presentation.post_list.Post

sealed interface FavoritesUiState {
    data object Loading : FavoritesUiState
    data object Empty : FavoritesUiState
    data class Success(val posts: List<Post>) : FavoritesUiState
}