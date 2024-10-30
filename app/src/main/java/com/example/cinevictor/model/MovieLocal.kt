package com.example.cinevictor.model

import androidx.annotation.DrawableRes

data class MovieLocal(
    val title: String,
    val year: Int,
    @DrawableRes val image: Int
)
