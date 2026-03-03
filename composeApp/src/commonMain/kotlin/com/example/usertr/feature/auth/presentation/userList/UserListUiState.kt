package com.example.usertr.feature.auth.presentation.userList

sealed interface UserListUiState {
    data object Loading : UserListUiState
    data class Success(
        val users: List<User>,
        val isOffline: Boolean = false
    ) : UserListUiState
    data class Error(val message: String) : UserListUiState
}