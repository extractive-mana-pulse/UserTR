package com.example.usertr.feature.home.data

import com.example.usertr.core.network.NetworkError
import com.example.usertr.core.network.PostDto
import com.example.usertr.core.network.Result
import com.example.usertr.core.network.constructUrl
import com.example.usertr.core.network.safeCall
import com.example.usertr.feature.home.domain.PostDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PostDataSourceImpl(
    private val httpClient: HttpClient
) : PostDataSource {

    override suspend fun getPostsByUser(userId: Int): Result<List<PostDto>, NetworkError> {
        return safeCall<List<PostDto>> {
            httpClient.get(constructUrl("/posts")) {
                parameter("userId", userId)
            }
        }
    }
}