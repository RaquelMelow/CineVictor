package com.example.cinevictor.presentation.features.popular.films.model

import androidx.annotation.DrawableRes

data class MovieData(
    val rowTitle: String,
    @DrawableRes val posters: List<Int>
)