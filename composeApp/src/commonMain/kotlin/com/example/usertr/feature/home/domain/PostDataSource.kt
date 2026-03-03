package com.example.usertr.feature.home.domain

import com.example.usertr.core.network.NetworkError
import com.example.usertr.core.network.PostDto
import com.example.usertr.core.network.Result

interface PostDataSource {
    suspend fun getPostsByUser(userId: Int): Result<List<PostDto>, NetworkError>
}