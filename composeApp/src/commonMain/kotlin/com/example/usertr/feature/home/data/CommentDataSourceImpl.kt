package com.example.usertr.feature.home.data

import com.example.usertr.core.network.NetworkError
import com.example.usertr.core.network.Result
import com.example.usertr.core.network.constructUrl
import com.example.usertr.core.network.safeCall
import com.example.usertr.feature.home.domain.CommentDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CommentDataSourceImpl(
    private val httpClient: HttpClient
) : CommentDataSource {

    override suspend fun getCommentsByPost(postId: Int): Result<List<CommentsDto>, NetworkError> {
        return safeCall<List<CommentsDto>> {
            httpClient.get(constructUrl("/comments")) {
                parameter("postId", postId)
            }
        }
    }
}