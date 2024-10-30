package com.example.cinevictor.domain.model

import androidx.annotation.DrawableRes

data class MovieLocal(
    val title: String,
    val year: Int,
    @DrawableRes val image: Int
)
