package com.example.cinevictor.presentation.features.popular.reviews.model

import com.example.cinevictor.model.Movie
import com.example.cinevictor.model.User

data class ReviewData(
    val id: String,
    val user: User,
    val movie: Movie,
    val rating: Int,
    val comment: String,
    val date: String
)