package com.example.cinevictor.presentation.features.popular.reviews.viewModel

import com.example.cinevictor.BuildConfig
import com.example.cinevictor.core.framework.network.retrofit.ReviewService
import com.example.cinevictor.core.framework.network.retrofit.safeCall
import com.example.cinevictor.data.local.dao.MovieDao
import com.example.cinevictor.data.local.dao.ReviewDao
import com.example.cinevictor.data.local.database.ReviewWithMovie
import com.example.cinevictor.data.local.database.toEntity
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

    fun getReviewsWithMovies(page: Int = 1): Flow<ApiResult<List<ReviewWithMovie>, DataError>> {
        return flow {
            val localReviews = reviewDao.getAllReviews().first()

            if (localReviews.isNotEmpty()) {
                val reviewsWithMovies = localReviews.map { reviewEntity ->
                    val movie = movieDao.getMovieById(reviewEntity.movieId)
                    ReviewWithMovie(
                        reviewId = reviewEntity.uid,
                        author = reviewEntity.author,
                        content = reviewEntity.content,
                        rating = reviewEntity.rating,
                        authorAvatar = reviewEntity.avatarPath,
                        movieTitle = movie?.name ?: "Unknown",
                        movieReleaseDate = movie?.releaseDate ?: "Unknown",
                        moviePosterUrl = movie?.posterUrl ?: ""
                    )
                }
                emit(ApiResult.Success(reviewsWithMovies))
            }

            when (val result = fetchReviewsFromApi(page)) {
                is ApiResult.Success -> {
                    result.data.forEach { review ->
                        reviewDao.insertReviews(listOf(review.toEntity(movieId = review.movieId)))
                    }

                    val reviewsWithMovies = result.data.map { review ->
                        val movie = movieDao.getMovieById(review.movieId)
                        ReviewWithMovie(
                            reviewId = review.id,
                            author = review.author,
                            content = review.content,
                            rating = review.rating,
                            authorAvatar = review.authorAvatarPath,
                            movieTitle = movie?.name ?: "Unknown",
                            movieReleaseDate = movie?.releaseDate ?: "Unknown",
                            moviePosterUrl = movie?.posterUrl ?: ""
                        )
                    }

                    emit(ApiResult.Success(reviewsWithMovies))
                }

                is ApiResult.Error -> {
                    emit(ApiResult.Error(result.error))
                }
            }
        }
    }

    suspend fun fetchReviewsFromApi(page: Int = 1): ApiResult<List<Review>, DataError> {
        return safeCall {
            reviewService.getReviewsByMovieId(BuildConfig.API_KEY, page)
        }.map { response ->
            response.reviews.toDomainList()
        }
    }

}

