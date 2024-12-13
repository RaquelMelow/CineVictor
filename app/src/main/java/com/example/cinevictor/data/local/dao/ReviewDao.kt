package com.example.cinevictor.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.cinevictor.data.local.database.ReviewEntity
import com.example.cinevictor.data.local.database.ReviewWithMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewEntity>)

    @Query("SELECT * FROM reviews WHERE movie_id = :movieId")
    suspend fun getReviewsByMovieId(movieId: Int): List<ReviewEntity>

    @Query("SELECT * FROM reviews")
    fun getAllReviews(): Flow<List<ReviewEntity>>

    @Transaction
    @Query("""
        SELECT 
            r.uid AS reviewId,
            r.author AS author,
            r.content AS content,
            r.rating AS reviewRating,
            r.avatar_path AS authorAvatar,
            m.name AS movieTitle,
            m.release_date AS releaseDate,
            m.posterUrl AS moviePosterUrl
        FROM reviews r
        LEFT JOIN movies m ON r.movie_id = m.uid
        WHERE r.movie_id = :movieId
    """)
    suspend fun getReviewsWithMovie(movieId: Int): List<ReviewWithMovie>
}
