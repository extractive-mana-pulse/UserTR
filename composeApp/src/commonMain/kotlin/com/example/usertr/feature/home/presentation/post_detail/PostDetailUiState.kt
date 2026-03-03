package com.example.usertr.feature.home.presentation.post_detail

import com.example.usertr.feature.home.presentation.post_list.Post

sealed interface PostDetailUiState {
    data object Loading : PostDetailUiState
    data class Success(val post: Post, val comments: List<Comment>) : PostDetailUiState
    data class Error(val message: String) : PostDetailUiState
}
