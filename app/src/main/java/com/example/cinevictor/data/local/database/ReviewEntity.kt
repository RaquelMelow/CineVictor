package com.example.cinevictor.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinevictor.domain.model.Review

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "author_name") val authorName: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "avatar_path") val avatarPath: String?,
    @ColumnInfo(name = "rating") val rating: Double?
)

data class ReviewWithMovie(
    val reviewId: String,
    val author: String,
    val content: String,
    val rating: Double?,
    val authorAvatar: String?,
    val movieTitle: String,
    val releaseDate: String,
    val moviePosterUrl: String
)

fun Review.toEntity(movieId: Int): ReviewEntity {
    return ReviewEntity(
        uid = this.id,
        movieId = movieId,
        author = this.author,
        content = this.content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        url = this.url,
        authorName = this.authorName,
        username = this.authorUsername,
        avatarPath = this.authorAvatarPath,
        rating = this.rating
    )
}


fun ReviewEntity.toDomain() = Review(
    id = uid,
    author = author,
    content = content,
    createdAt = createdAt,
    updatedAt = updatedAt,
    url = url,
    authorName = authorName,
    authorUsername = username,
    authorAvatarPath = avatarPath,
    rating = rating,
    movieId = movieId
)