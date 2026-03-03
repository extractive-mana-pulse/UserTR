package com.example.usertr.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts WHERE userId = :userId")
    fun observePostsByUser(userId: Int): Flow<List<PostEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("SELECT * FROM favorites")
    fun observeFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE postId = :postId)")
    suspend fun isFavorite(postId: Int): Boolean

    @Insert(onConflict = REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE postId = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM posts WHERE id = :postId")
    fun observePostById(postId: Int): Flow<PostEntity?>

    @Query("SELECT * FROM posts")
    fun observeAllPosts(): Flow<List<PostEntity>>
}