package com.example.usertr.feature.home.data

import com.example.usertr.core.database.FavoriteEntity
import com.example.usertr.core.database.PostDao
import com.example.usertr.core.mappers.toDomain
import com.example.usertr.core.mappers.toEntity
import com.example.usertr.feature.home.presentation.post_list.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostLocalDataSource(private val postDao: PostDao) {

    fun observePostsByUser(userId: Int): Flow<List<Post>> {
        return postDao.observePostsByUser(userId).map { list ->
            list.map { it.toDomain() }
        }
    }

    suspend fun savePosts(posts: List<Post>) {
        postDao.insertAll(posts.map { it.toEntity() })
    }

    fun observeFavorites(): Flow<List<Int>> {
        return postDao.observeFavorites().map { list ->
            list.map { it.postId }
        }
    }

    suspend fun toggleFavorite(postId: Int) {
        if (postDao.isFavorite(postId)) {
            postDao.deleteFavorite(postId)
        } else {
            postDao.insertFavorite(FavoriteEntity(postId))
        }
    }

    fun observePostById(postId: Int): Flow<Post?> {
        return postDao.observePostById(postId).map { it?.toDomain() }
    }

    fun observeAllPosts(): Flow<List<Post>> {
        return postDao.observeAllPosts().map { list ->
            list.map { it.toDomain() }
        }
    }
}