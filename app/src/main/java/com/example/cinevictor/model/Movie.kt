package com.example.cinevictor.model

import androidx.annotation.DrawableRes

data class Movie(
    val title: String,
    val year: Int,
    @DrawableRes val image: Int
)