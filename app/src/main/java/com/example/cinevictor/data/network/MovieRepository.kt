package com.example.cinevictor.data.network

import com.example.cinevictor.BuildConfig
import com.example.cinevictor.data.model.movie.toDomain
import com.example.cinevictor.data.model.toDomainList
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.model.MovieDetailsCredit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val service: MovieService) {

    suspend fun getPopularMovies(page: Int = 1): List<Movie>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getPopularMovies(BuildConfig.API_KEY, page)

                if (response.isSuccessful) {
                    response.body()?.results?.toDomainList()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getMovieCredits(movieId: Int): MovieDetailsCredit? {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getDetailCreditMovie(movieId, BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    response.body()?.toDomain()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

}
