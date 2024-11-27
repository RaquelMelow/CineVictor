package com.example.cinevictor.data.network

import com.example.cinevictor.data.network.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<PopularMoviesResponse>

}
