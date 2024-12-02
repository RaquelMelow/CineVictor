package com.example.cinevictor.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cinevictor.data.local.database.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE uid = :id")
    suspend fun getMovieById(id: Int): MovieEntity?


    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>
}