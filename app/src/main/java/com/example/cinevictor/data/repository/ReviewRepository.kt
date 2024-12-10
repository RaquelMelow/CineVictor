package com.example.cinevictor.data.repository

import com.example.cinevictor.BuildConfig
import com.example.cinevictor.core.framework.network.retrofit.ReviewService
import com.example.cinevictor.core.framework.network.retrofit.safeCall
import com.example.cinevictor.data.local.dao.MovieDao
import com.example.cinevictor.data.local.dao.ReviewDao
import com.example.cinevictor.data.local.database.MovieEntity
import com.example.cinevictor.data.local.database.ReviewEntity
import com.example.cinevictor.data.local.database.ReviewWithMovie
import com.example.cinevictor.data.local.database.toDomain
import com.example.cinevictor.data.local.database.toEntity
import com.example.cinevictor.data.network.model.toDomainList
import com.example.cinevictor.domain.model.Review
import com.example.cinevictor.domain.util.ApiResult
import com.example.cinevictor.domain.util.DataError
import com.example.cinevictor.domain.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ReviewRepository(
    private val reviewService: ReviewService,
    private val reviewDao: ReviewDao,
    private val movieDao: MovieDao,
) {

    fun getReviewsByMovieId(movieId: Int, page: Int = 1): Flow<ApiResult<List<ReviewWithMovie>, DataError>> {
        return flow {
            val localReviews = reviewDao.getReviewsByMovieId(movieId)

            if (localReviews.isNotEmpty()) {
                val reviewsWithMovies = localReviews.map { reviewEntity ->
                    val movie = movieDao.getMovieById(reviewEntity.movieId)
                    mapReviewWithMovie(reviewEntity, movie)
                }

                emit(ApiResult.Success(reviewsWithMovies))
            }

            when (val result = fetchReviewsFromApi(movieId, page)) {
                is ApiResult.Success -> {
                    result.data.forEach { review ->
                        reviewDao.insertReviews(listOf(review.toEntity(movieId)))
                    }

                    val reviewsWithMovies = result.data.map { review ->
                        val movie = movieDao.getMovieById(movieId)
                        mapReviewWithMovie(review.toEntity(movieId), movie)
                    }

                    emit(ApiResult.Success(reviewsWithMovies))
                }

                is ApiResult.Error -> {
                    emit(ApiResult.Error(result.error))
                }
            }
        }
    }

    private fun mapReviewWithMovie(reviewEntity: ReviewEntity, movieEntity: MovieEntity?): ReviewWithMovie {
        return ReviewWithMovie(
            reviewId = reviewEntity.uid,
            author = reviewEntity.author,
            content = reviewEntity.content,
            rating = reviewEntity.rating,
            authorAvatar = reviewEntity.avatarPath,
            movieTitle = movieEntity?.name ?: "Unknown",
            releaseDate = movieEntity?.releaseDate ?: "Unknown",
            moviePosterUrl = movieEntity?.posterUrl ?: ""
        )
    }

    suspend fun fetchReviewsFromApi(movieId: Int, page: Int = 1): ApiResult<List<Review>, DataError.Network> {
        return safeCall {
            reviewService.getReviewsByMovieId(movieId, BuildConfig.API_KEY, page)
        }.map {
            it.reviews.toDomainList(movieId)
        }
    }

    suspend fun getAllReviews(): Flow<ApiResult<List<Review>, DataError>> {
        return flow {
            val localReviews = reviewDao.getAllReviews().first()
            emit(ApiResult.Success(localReviews.map { it.toDomain() }))
        }
    }
}

