package com.example.usertr.feature.home.domain

import com.example.usertr.core.network.NetworkError
import com.example.usertr.core.network.Result
import com.example.usertr.feature.home.data.CommentsDto

interface CommentDataSource {
    suspend fun getCommentsByPost(postId: Int): Result<List<CommentsDto>, NetworkError>
}