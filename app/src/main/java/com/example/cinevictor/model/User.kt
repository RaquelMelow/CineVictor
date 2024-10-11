package com.example.cinevictor.model

import androidx.annotation.DrawableRes

data class User(
    val id: String,
    val name: String,
    @DrawableRes val image: Int
)