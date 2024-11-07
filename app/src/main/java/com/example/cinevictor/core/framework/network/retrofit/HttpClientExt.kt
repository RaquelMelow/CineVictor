package com.example.cinevictor.core.framework.network.retrofit

import com.example.cinevictor.core.framework.network.interceptors.InternetConnectivityException
import com.example.cinevictor.domain.util.ApiResult
import com.example.cinevictor.domain.util.DataError.Network
import retrofit2.Response

inline fun <reified T> safeCall(
    execute: () -> Response<T>
): ApiResult<T, Network> {
    return try {
        val response = execute()

        when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null) {
                    ApiResult.Success(body)
                } else {
                    ApiResult.Error(Network.UNKNOWN)
                }
            }

            else -> {
                when (response.code()) {
                    400 -> ApiResult.Error(Network.BAD_REQUEST)
                    401 -> ApiResult.Error(Network.UNAUTHORIZED)
                    408 -> ApiResult.Error(Network.REQUEST_TIMEOUT)
                    409 -> ApiResult.Error(Network.CONFLICT)
                    413 -> ApiResult.Error(Network.PAYLOAD_TOO_LARGE)
                    429 -> ApiResult.Error(Network.TOO_MANY_REQUESTS)
                    in 500..599 -> ApiResult.Error(Network.SERVER_ERROR)
                    else -> ApiResult.Error(Network.UNKNOWN)
                }
            }
        }
    } catch (e: InternetConnectivityException) {
        ApiResult.Error(Network.NO_INTERNET)
    }
}