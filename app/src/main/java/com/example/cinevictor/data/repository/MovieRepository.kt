package com.example.cinevictor.data.repository
import com.example.cinevictor.BuildConfig
import com.example.cinevictor.core.framework.network.retrofit.MovieService
import com.example.cinevictor.core.framework.network.retrofit.safeCall
import com.example.cinevictor.data.local.dao.MovieDao
import com.example.cinevictor.data.local.database.toDomain
import com.example.cinevictor.data.local.database.toEntity
import com.example.cinevictor.data.model.movie.toDomain
import com.example.cinevictor.data.network.model.toDomainList
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.model.MovieDetailsCredit
import com.example.cinevictor.domain.util.ApiResult
import com.example.cinevictor.domain.util.DataError
import com.example.cinevictor.domain.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val service: MovieService,
    private val movieDao: MovieDao
) {

    fun getPopularMovies(page: Int = 1): Flow<ApiResult<List<Movie>, DataError>> {
        return flow {

            val localMovies = movieDao.getAllMovies().first()

            emit(ApiResult.Success(localMovies.map { it.toDomain() }))

            if (localMovies.isEmpty()) {
                when (val result = fetchPopularMovies(page)) {
                    is ApiResult.Success -> {

                        result.data.forEach {
                            movieDao.insertMovie(it.toEntity())
                        }

                        //movieDao.insertMovies(result.data.map { it.toEntity() })

                        emit(ApiResult.Success(result.data))
                    }

                    is ApiResult.Error -> {
                        emit(ApiResult.Error(result.error))
                    }
                }
            }
        }
    }


    suspend fun fetchPopularMovies(page: Int = 1): ApiResult<List<Movie>, DataError.Network> {
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

    suspend fun getUpcomingMovies(page: Int): ApiResult<List<Movie>, DataError> {
        return try {
            val response = service.getUpcomingMovies(BuildConfig.API_KEY, page)

            if (response.isSuccessful) {
                ApiResult.Success(response.body()?.results?.toDomainList() ?: emptyList())
            } else {
                ApiResult.Error(DataError.Network.UNKNOWN)
            }
        } catch (e: Exception) {
            ApiResult.Error(DataError.Network.UNKNOWN)
        }
    }

}

