package com.example.usertr.feature.home.presentation.post_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usertr.core.network.Result
import com.example.usertr.feature.home.data.PostLocalDataSource
import com.example.usertr.feature.home.domain.PostDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PostListViewModel(
    private val userId: Int,
    private val remoteDataSource: PostDataSource,
    private val localDataSource: PostLocalDataSource
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    private val _isOffline = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    val uiState: StateFlow<PostListUiState> = combine(
        localDataSource.observePostsByUser(userId),
        localDataSource.observeFavorites(),
        _isLoading,
        _isOffline,
        _error
    ) { posts, favorites, loading, offline, error ->
        when {
            loading && posts.isEmpty() -> PostListUiState.Loading
            error != null && posts.isEmpty() -> PostListUiState.Error(error)
            else -> PostListUiState.Success(
                posts = posts,
                favoriteIds = favorites.toSet(),
                isOffline = offline
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PostListUiState.Loading
    )

    init {
        loadPosts()
    }

    fun refresh() {
        viewModelScope.launch {
            _error.value = null

            when (val result = remoteDataSource.getPostsByUser(userId)) {
                is Result.Success -> {
                    localDataSource.savePosts(result.data.map { post ->
                        Post(
                            id = post.id,
                            userId = post.userId,
                            title = post.title,
                            body = post.body
                        )
                    })
                    _isOffline.value = false
                }
                is Result.Error -> {
                    _isOffline.value = true
                }
            }
        }
    }

    fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            when (val result = remoteDataSource.getPostsByUser(userId)) {
                is Result.Success -> {
                    localDataSource.savePosts(
                        result.data.map {
                            Post(
                                id = it.id,
                                userId = it.userId,
                                title = it.title,
                                body = it.body
                            )
                        }
                    )
                    _isOffline.value = false
                }
                is Result.Error -> {
                    _isOffline.value = true
                    _error.value = result.error.name
                }
            }
            _isLoading.value = false
        }
    }

    fun toggleFavorite(post: Post) {
        viewModelScope.launch {
            localDataSource.toggleFavorite(post.id)
        }
    }
}