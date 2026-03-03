package com.example.usertr.feature.auth.domain

import com.example.usertr.core.network.NetworkError
import com.example.usertr.core.network.Result
import com.example.usertr.core.network.UserDto

interface UserDataSource {
    suspend fun getUsers(): Result<List<UserDto>, NetworkError>
}