package com.example.cinevictor.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val genreIds: List<Int>,
    val originCountry: String,
)