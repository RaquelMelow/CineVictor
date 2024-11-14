package com.example.cinevictor.data.model.movie

import com.example.cinevictor.domain.model.CastMember
import com.example.cinevictor.domain.model.MovieDetailsCredit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailCreditResponse(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "belongs_to_collection") val belongsToCollection: Collection?,
    @Json(name = "budget") val budget: Int,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "homepage") val homepage: String?,
    @Json(name = "id") val id: Int,
    @Json(name = "imdb_id") val imdbId: String?,
    @Json(name = "origin_country") val originCountry: List<String>,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompany>,
    @Json(name = "production_countries") val productionCountries: List<ProductionCountry>,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "revenue") val revenue: Int,
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String?,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "credits") val credits: Credits?
)

@JsonClass(generateAdapter = true)
data class Collection(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?
)

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    @Json(name = "id") val id: Int,
    @Json(name = "logo_path") val logoPath: String?,
    @Json(name = "name") val name: String,
    @Json(name = "origin_country") val originCountry: String
)

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    @Json(name = "iso_3166_1") val iso3166_1: String,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @Json(name = "english_name") val englishName: String,
    @Json(name = "iso_639_1") val iso6391: String,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class Credits(
    @Json(name = "cast") val cast: List<Cast>
)

@JsonClass(generateAdapter = true)
data class Cast(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "gender") val gender: Int?,
    @Json(name = "id") val id: Int,
    @Json(name = "known_for_department") val knownForDepartment: String,
    @Json(name = "name") val name: String,
    @Json(name = "original_name") val originalName: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "cast_id") val castId: Int,
    @Json(name = "character") val character: String,
    @Json(name = "credit_id") val creditId: String,
    @Json(name = "order") val order: Int,
    @Json(name = "job") val job: String?
)


fun MovieDetailCreditResponse.toDomain(): MovieDetailsCredit {
    return MovieDetailsCredit(
        id = id,
        title = originalTitle,
        backdropPath = "https://image.tmdb.org/t/p/w500$backdropPath",
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath",
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        runtime = runtime,
        genres = genres.map { it.name },
        cast = credits?.cast?.map { it.toDomain() } ?: emptyList()
    )
}

fun Cast.toDomain(): CastMember {
    return CastMember(
        id = id,
        name = name,
        character = character,
        job = job ?: "Unknown",
        profilePath = "https://image.tmdb.org/t/p/w500$profilePath"
    )
}