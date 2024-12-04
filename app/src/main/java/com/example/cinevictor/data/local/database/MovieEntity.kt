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
    @ColumnInfo(name = "release_date") val releaseDate: String?)

fun Movie.toEntity() = MovieEntity(
    uid = id,
    name = title,
    posterUrl = posterPath,
    overview = overview,
    releaseDate = year
)

fun MovieEntity.toDomain() = Movie(
    id = uid,
    title = name ?: "",
    posterPath = posterUrl ?: "",
    overview = overview ?: "",
    year = releaseDate ?: ""
)

fun MovieEntity.toMovieDomain() = Movie(
    id = uid,
    title = name ?: "",
    posterPath = posterUrl ?: "",
    overview = overview ?: "",
    year = releaseDate ?: ""
)

fun Cast.toCastDomain() = CastMember(
    id = id,
    name = name ?: "",
    profilePath = profilePath ?: "",
    character = character ?: "Unknown Character",
    job = job ?: "Unknown Job"
)
