package com.example.usertr.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screens {

    @Serializable
    data object Splash : Screens {
        @Serializable
        data object Retry : Screens

        @Serializable
        data object UserList : Screens
    }
    @Serializable
    data class PostList(
        val userId: Int,
        val userName: String
    ) : Screens

    @Serializable
    data class PostDetail(
        val postId: Int
    ) : Screens

    @Serializable
    data object Favorites : Screens
}