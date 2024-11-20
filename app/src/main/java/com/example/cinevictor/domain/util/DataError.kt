package com.example.cinevictor.domain.util

sealed interface DataError: Error {
    enum class Network : DataError {
        BAD_REQUEST,
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL
    }
}

fun DataError.Network.toMessage(): String {
    return when (this) {
        DataError.Network.BAD_REQUEST -> "Bad request. Please check your data."
        DataError.Network.UNAUTHORIZED -> "Unauthorized access. Please log in."
        DataError.Network.REQUEST_TIMEOUT -> "Request timed out. Please try again later."
        DataError.Network.CONFLICT -> "Conflict occurred. Please try again."
        DataError.Network.PAYLOAD_TOO_LARGE -> "The request payload is too large."
        DataError.Network.TOO_MANY_REQUESTS -> "Too many requests. Please try again later."
        DataError.Network.SERVER_ERROR -> "Server error occurred. Please try again later."
        DataError.Network.NO_INTERNET -> "No internet connection. Please check your network."
        DataError.Network.SERIALIZATION -> "Failed to parse response."
        DataError.Network.UNKNOWN -> "An unknown error occurred. Please try again later."
    }
}
