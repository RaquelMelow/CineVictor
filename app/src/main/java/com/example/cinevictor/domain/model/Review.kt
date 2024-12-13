package com.example.cinevictor.domain.model

data class Review(
    val id: String,
    val author: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val url: String,
    val authorName: String?,
    val authorUsername: String?,
    val authorAvatarPath: String?,
    val rating: Double?,
    val movieId: Int,
)