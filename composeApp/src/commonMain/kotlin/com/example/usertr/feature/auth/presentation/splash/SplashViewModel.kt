package com.example.usertr.feature.auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usertr.core.network.Result
import com.example.usertr.feature.auth.data.UserLocalDataSource
import com.example.usertr.feature.auth.domain.UserDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserDataSource
) : ViewModel() {

    private val _destination = MutableStateFlow<SplashDestination>(SplashDestination.Loading)
    val destination = _destination.asStateFlow()

    init {
        viewModelScope.launch {
            if (localDataSource.hasUsers()) {
                _destination.value = SplashDestination.UserList
            } else {
                when (remoteDataSource.getUsers()) {
                    is Result.Success -> {
                        _destination.value = SplashDestination.UserList
                    }
                    is Result.Error -> {
                        _destination.value = SplashDestination.Retry
                    }
                }
            }
        }
    }
}