package com.example.cinevictor.core.framework.network.interceptors

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(
    private val connectivityManager: ConnectivityManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) throw  InternetConnectivityException()
        val request = chain.request()
        return chain.proceed(request)
    }

    private fun isConnected(): Boolean =
        connectivityManager.activeNetwork?.let { activeNetwork ->
            connectivityManager.getNetworkCapabilities(activeNetwork)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false
}

class InternetConnectivityException: IOException()