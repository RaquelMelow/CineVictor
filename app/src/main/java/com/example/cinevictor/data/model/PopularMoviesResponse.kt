package com.example.cinevictor.data.model

import com.example.cinevictor.data.model.movie.MovieResponse
import com.example.cinevictor.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMoviesResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<MovieResponse>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int,
)

fun List<MovieResponse>.toDomainList(): List<Movie> {
    return this.map { movie -> movie.toDomain() }
}

fun MovieResponse.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath"
    )
}

