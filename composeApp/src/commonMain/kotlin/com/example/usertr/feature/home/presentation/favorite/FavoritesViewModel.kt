package com.example.usertr.feature.home.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usertr.feature.home.data.PostLocalDataSource
import com.example.usertr.feature.home.presentation.post_list.Post
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val localDataSource: PostLocalDataSource
) : ViewModel() {

    val uiState: StateFlow<FavoritesUiState> = combine(
        localDataSource.observeAllPosts(),
        localDataSource.observeFavorites()
    ) { posts, favoriteIds ->
        val favoritePosts = posts.filter { it.id in favoriteIds }
        if (favoritePosts.isEmpty()) FavoritesUiState.Empty
        else FavoritesUiState.Success(favoritePosts)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoritesUiState.Loading
    )

    fun toggleFavorite(post: Post) {
        viewModelScope.launch {
            localDataSource.toggleFavorite(post.id)
        }
    }
}