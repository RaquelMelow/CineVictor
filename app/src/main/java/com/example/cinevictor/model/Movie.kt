package model

import androidx.annotation.DrawableRes

data class Review(
    val id: String,
    val user: User,
    val movie: Movie,
    val rating: Int,
    val comment: String,
    val date: String
)

data class User(
    val id: String,
    val name: String,
    @DrawableRes val image: Int
)

data class Movie(
    val title: String,
    val year: Int,
    @DrawableRes val image: Int
)