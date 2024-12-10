package com.example.cinevictor.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinevictor.data.model.movie.Cast
import com.example.cinevictor.domain.model.CastMember
import com.example.cinevictor.domain.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "posterUrl") val posterUrl: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "releasedate") val releaseDate: String,
    @ColumnInfo(name = "genres") val genres: Int,
    @ColumnInfo(name = "origin_country") val originCountry: String
)

fun Movie.toEntity() = MovieEntity(
    uid = id,
    name = title,
    posterUrl = posterPath,
    overview = overview,
    releaseDate = releaseDate,
    genres = genreIds.first(),
    originCountry = originCountry
)

fun MovieEntity.toDomain() = Movie(
    id = uid,
    title = name ?: "",
    posterPath = posterUrl ?: "",
    overview = overview ?: "",
    releaseDate = releaseDate,
    genreIds = listOf(genres),
    originCountry = originCountry
)

fun Cast.toCastDomain() = CastMember(
    id = id,
    name = name ?: "",
    profilePath = profilePath ?: "",
    character = character ?: "Unknown Character",
    job = job ?: "Unknown Job"
)
