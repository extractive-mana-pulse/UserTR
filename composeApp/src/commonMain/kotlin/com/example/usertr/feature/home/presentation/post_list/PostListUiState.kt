package com.example.usertr.feature.home.presentation.post_list

sealed interface PostListUiState {
    data object Loading : PostListUiState
    data class Success(
        val posts: List<Post>,
        val favoriteIds: Set<Int> = emptySet(),
        val isOffline: Boolean = false
    ) : PostListUiState
    data class Error(val message: String) : PostListUiState
}