/*
package com.example.cinevictor.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.cinevictor.data.repository.MovieRepository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppModule {
    val okHttpClient: OkHttpClient
    val retrofit: Retrofit
    val movieRepository: MovieRepository
    val moshi: Moshi
    val connectivityManager: ConnectivityManager
}

private const val BASE_URL = "https://api.themoviedb.org/3/"

class AppModuleImpl(
    private val appContext: Context,
    override val okHttpClient: OkHttpClient,
    override val retrofit: Retrofit,
    override val movieRepository: MovieRepository,
    override val moshi: Moshi,
    override val connectivityManager: ConnectivityManager
): AppModule {

}*/
