package com.example.cinevictor.domain.model

import androidx.annotation.DrawableRes

data class User(
    val id: String,
    val name: String,
    @DrawableRes val image: Int
)