package com.example.cinevictor.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cinevictor.domain.model.MovieRoom

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: MovieRoom)

    @Update
    suspend fun updateMovie(movie: MovieRoom)

    @Query("SELECT * FROM movies WHERE uid = :id")
    suspend fun getMovieById(id: Int): MovieRoom?


    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieRoom>
}