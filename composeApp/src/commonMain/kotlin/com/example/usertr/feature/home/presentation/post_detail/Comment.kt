package com.example.usertr.feature.home.presentation.post_detail

data class Comment(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)