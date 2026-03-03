package com.example.usertr.feature.home.data

import kotlinx.serialization.Serializable

@Serializable
data class CommentsDto (
  val postId: Int,
  val id: Int,
  val name: String,
  val email: String,
  val body: String
)