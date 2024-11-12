package com.example.cinevictor.presentation.features.popular.films.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.network.MovieRepository
import com.example.cinevictor.data.network.MovieService
import com.example.cinevictor.data.network.RetrofitClient
import com.example.cinevictor.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmsViewModel : ViewModel() {

    private val movieService = RetrofitClient.retrofit.create(MovieService::class.java)
    private val repository = MovieRepository(movieService)

    private val _popularOfTheWeek = MutableStateFlow<List<Movie>>(emptyList())
    val popularOfTheWeek: StateFlow<List<Movie>> = _popularOfTheWeek

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val response = repository.getPopularMovies()

            response?.let {
                _popularOfTheWeek.value = response
            }
        }
    }
}