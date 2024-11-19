package com.example.cinevictor.data.repository
import com.example.cinevictor.BuildConfig
import com.example.cinevictor.core.framework.network.retrofit.MovieService
import com.example.cinevictor.core.framework.network.retrofit.safeCall
import com.example.cinevictor.data.model.movie.toDomain
import com.example.cinevictor.data.model.toDomainList
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.model.MovieDetailsCredit
import com.example.cinevictor.domain.util.ApiResult
import com.example.cinevictor.domain.util.DataError
import com.example.cinevictor.domain.util.map

class MovieRepository(private val service: MovieService) {

    suspend fun getPopularMovies(page: Int = 1): ApiResult<List<Movie>, DataError.Network> {
        return safeCall {
            service.getPopularMovies(BuildConfig.API_KEY, page)
        }.map {
            it.results.toDomainList()
        }
    }

    suspend fun getMovieDetails(id: Int): ApiResult<MovieDetailsCredit, DataError.Network> {
        return safeCall {
            service.getDetailCreditMovie(id, BuildConfig.API_KEY)
        }.map {
            it.toDomain()

        }
    }
}

