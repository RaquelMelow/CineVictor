package com.example.cinevictor.core.framework.network.retrofit

import com.example.cinevictor.data.network.model.ReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsByMovieId(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Response<ReviewResponse>
}