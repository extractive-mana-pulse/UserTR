package com.example.usertr.feature.auth.data

import com.example.usertr.core.database.UserDao
import com.example.usertr.core.mappers.toDomain
import com.example.usertr.feature.auth.presentation.userList.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSource(private val userDao: UserDao) {

    fun observeUsers(): Flow<List<User>> {
        return userDao.observeUsers().map { list ->
            list.map { it.toDomain() }
        }
    }

    suspend fun saveUsers(users: List<User>) {
        userDao.insertAll(users.map { 
            it.toEntity() 
        })
    }

    suspend fun hasUsers(): Boolean {
        return userDao.getUserCount() > 0
    }
}

fun User.toEntity() = com.example.usertr.core.database.UserEntity(
    id = id, name = name, username = username, email = email
)