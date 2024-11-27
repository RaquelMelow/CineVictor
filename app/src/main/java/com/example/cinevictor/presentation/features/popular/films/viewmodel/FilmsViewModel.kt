package com.example.cinevictor.presentation.features.films.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.core.framework.network.retrofit.RetrofitClient
import com.example.cinevictor.data.network.MovieService
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmsViewModel(
    repository: MovieRepository
) : ViewModel() {

    private val movieService = RetrofitClient.retrofit.create(MovieService::class.java)
    private val repository = MovieRepository(movieService)

    private var currentPage = 1
    private val _popularOfTheWeek = MutableStateFlow<List<Movie>>(emptyList())
    val popularOfTheWeek: StateFlow<List<Movie>> = _popularOfTheWeek

    init {
        loadMovies()
    }

     fun loadMovies() {
        viewModelScope.launch {
            val response = repository.getPopularMovies(currentPage)
            when(val result = repository.getPopularMovies()) {
                is ApiResult.Error -> {

            response?.let {
                _popularOfTheWeek.value += it
                currentPage ++
                }

                is ApiResult.Success -> {
                    _popularOfTheWeek.value = result.data
                }
            }
        }
    }
}