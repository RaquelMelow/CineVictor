package com.example.cinevictor.data.network.model

import com.example.cinevictor.domain.model.Review
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    @Json(name = "id") val movieId: Int,
    @Json(name = "page") val page: Int,
    @Json(name = "results") val reviews: List<ReviewDto>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int

)

@JsonClass(generateAdapter = true)
data class ReviewDto(
    @Json(name = "id") val id: String,
    @Json(name = "author") val author: String,
    @Json(name = "author_details") val authorDetails: AuthorDetailsDto,
    @Json(name = "content") val content: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class AuthorDetailsDto(
    @Json(name = "name") val name: String?,
    @Json(name = "username") val username: String?,
    @Json(name = "avatar_path") val avatarPath: String?,
    @Json(name = "rating") val rating: Double?
)

fun List<ReviewDto>.toDomainList(movieId: Int): List<Review> {
    return this.map { reviewDto -> reviewDto.toDomain(movieId) }
}

fun getFullImageUrl(avatarPath: String?): String? {
    return if (avatarPath != null) {
        "https://image.tmdb.org/t/p/w200$avatarPath"
    } else null
}


fun ReviewDto.toDomain(movieId: Int) = Review(
    id = id,
    movieId = movieId,
    author = author,
    content = content,
    createdAt = createdAt,
    updatedAt = updatedAt,
    url = url,
    authorName = authorDetails.name.orEmpty(),
    authorUsername = authorDetails.username.orEmpty(),
    authorAvatarPath = getFullImageUrl(authorDetails.avatarPath),
    rating = authorDetails.rating
)