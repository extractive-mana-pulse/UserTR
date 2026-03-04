package com.example.usertr.feature.home.presentation.post_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usertr.core.network.Result
import com.example.usertr.feature.home.data.PostLocalDataSource
import com.example.usertr.feature.home.domain.CommentDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val postId: Int,
    private val localDataSource: PostLocalDataSource,
    private val commentDataSource: CommentDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostDetailUiState>(PostDetailUiState.Loading)
    val uiState: StateFlow<PostDetailUiState> = _uiState.asStateFlow()

    init {
        loadDetail()
    }

    fun loadDetail() {
        viewModelScope.launch {
            _uiState.value = PostDetailUiState.Loading

            val postDeferred = async {
                localDataSource.observePostById(postId).first()
            }
            val commentsDeferred = async {
                commentDataSource.getCommentsByPost(postId)
            }

            val post = postDeferred.await()
            val commentsResult = commentsDeferred.await()

            if (post == null) {
                _uiState.value = PostDetailUiState.Error("Post not found.")
                return@launch
            }

            _uiState.value = when (commentsResult) {
                is Result.Success -> PostDetailUiState.Success(
                    post = post,
                    comments = commentsResult.data.map {
                        Comment(
                            id = it.id,
                            postId = it.postId,
                            name = it.name,
                            email = it.email,
                            body = it.body
                        )
                    }
                )
                is Result.Error -> PostDetailUiState.Error(commentsResult.error.name)
            }
        }
    }
}