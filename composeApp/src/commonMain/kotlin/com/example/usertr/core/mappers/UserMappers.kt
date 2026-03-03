package com.example.usertr.core.mappers

import com.example.usertr.core.database.UserEntity
import com.example.usertr.core.network.UserDto
import com.example.usertr.feature.auth.presentation.userList.User

fun UserDto.toEntity() = UserEntity(
    id = id,
    name = name,
    username = username,
    email = email
)

fun UserEntity.toDomain() = User(
    id = id,
    name = name,
    username = username,
    email = email
)

fun UserDto.toDomain() = User(
    id = id,
    name = name,
    username = username,
    email = email
)