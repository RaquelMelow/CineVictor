package com.example.cinevictor.presentation.features.films.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.core.framework.network.retrofit.MovieService
import com.example.cinevictor.core.framework.network.retrofit.RetrofitClient
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmsViewModel : ViewModel() {

    val movieService = RetrofitClient.retrofit.create(MovieService::class.java)
    val repository = MovieRepository(movieService)

    private val _popularOfTheWeek = MutableStateFlow<List<Movie>>(emptyList())
    val popularOfTheWeek: StateFlow<List<Movie>> = _popularOfTheWeek

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            when(val result = repository.getPopularMovies()) {
                is ApiResult.Error -> {

                }

                is ApiResult.Success -> {
                    _popularOfTheWeek.value = result.data
                }
            }
        }
    }
}