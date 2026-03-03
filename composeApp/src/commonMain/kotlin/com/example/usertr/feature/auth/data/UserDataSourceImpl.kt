package com.example.usertr.feature.auth.data

import com.example.usertr.core.network.NetworkError
import com.example.usertr.core.network.Result
import com.example.usertr.core.network.UserDto
import com.example.usertr.core.network.constructUrl
import com.example.usertr.core.network.safeCall
import com.example.usertr.feature.auth.domain.UserDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class UserDataSourceImpl(
    private val httpClient: HttpClient
) : UserDataSource {

    override suspend fun getUsers(): Result<List<UserDto>, NetworkError> {
        return safeCall {
            httpClient.get(constructUrl("/users"))
        }
    }
}