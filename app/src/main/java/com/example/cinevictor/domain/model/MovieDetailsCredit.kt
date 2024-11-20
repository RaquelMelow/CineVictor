package com.example.cinevictor.domain.model

data class MovieDetailsCredit(
    val id: Int,
    val title: String,
    val backdropPath: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: Int?,
    val genres: List<String>,
    val cast: List<CastMember>,
    val directorName: String? = null
)

data class GenresList(
    val id: Int,
    val name: String
)

data class CastMember(
    val id: Int,
    val name: String,
    val character: String,
    val job: String,
    val profilePath: String
)

