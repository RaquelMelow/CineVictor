package com.example.cinevictor.data.network

import com.example.cinevictor.BuildConfig
import com.example.cinevictor.data.model.PopularMoviesResponse
import com.example.cinevictor.data.model.movie.MovieDetailCreditResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1,
    ): Response<PopularMoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<PopularMoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailCreditMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): Response<MovieDetailCreditResponse>


}