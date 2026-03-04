package com.example.usertr.feature.auth.presentation.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usertr.core.mappers.toDomain
import com.example.usertr.core.network.Result
import com.example.usertr.feature.auth.data.UserLocalDataSource
import com.example.usertr.feature.auth.domain.UserDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserListViewModel(
    private val remoteDataSource: UserDataSource,
    private val localDataSource: UserLocalDataSource
) : ViewModel() {

    private val _isOffline = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(true)

    val uiState: StateFlow<UserListUiState> = combine(
        localDataSource.observeUsers(),
        _isLoading,
        _isOffline,
        _error
    ) { users, loading, offline, error ->
        when {
            loading && users.isEmpty() -> UserListUiState.Loading
            error != null && users.isEmpty() -> UserListUiState.Error(error)
            else -> UserListUiState.Success(users, isOffline = offline)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserListUiState.Loading
    )

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            when (val result = remoteDataSource.getUsers()) {
                is Result.Success -> {
                    localDataSource.saveUsers(result.data.map { it.toDomain() })
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
}