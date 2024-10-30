package com.example.cinevictor.data.network

import MovieService
import com.example.cinevictor.data.model.movie.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val service: MovieService) {

    suspend fun getPopularMovies(apiKey: String, page: Int): List<MovieResponse>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getPopularMovies(apiKey, page)
                if (response.isSuccessful) {
                    response.body()?.results
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}
