package com.example.cinevictor.domain.util

sealed interface ApiResult<out D, out E> {
    data class Success<out D>(val data: D): ApiResult<D, Nothing>
    data class Error<out E>(val error: E): ApiResult<Nothing, E>
}

inline fun <T, E: Error, R> ApiResult<T, E>.map(map: (T) -> R): ApiResult<R, E> {
    return when(this) {
        is ApiResult.Error -> ApiResult.Error(error)
        is ApiResult.Success -> ApiResult.Success(map(data))
    }
}