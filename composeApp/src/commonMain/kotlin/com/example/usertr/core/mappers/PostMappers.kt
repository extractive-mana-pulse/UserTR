package com.example.usertr.core.mappers

import com.example.usertr.core.database.PostEntity
import com.example.usertr.core.network.PostDto
import com.example.usertr.feature.home.presentation.post_list.Post

fun PostDto.toEntity() = PostEntity(
    id = id,
    userId = userId,
    title = title,
    body = body
)

fun PostEntity.toDomain() = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)

fun PostDto.toDomain() = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)

fun Post.toEntity() = PostEntity(
    id = id,
    userId = userId,
    title = title,
    body = body
)