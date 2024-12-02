package com.example.cinevictor.presentation.features.films.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private var currentPage = 1
    private val _popularOfTheWeek = MutableStateFlow<List<Movie>>(emptyList())
    val popularOfTheWeek: StateFlow<List<Movie>> = _popularOfTheWeek

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            repository.getPopularMovies().collect { result ->
                when(result) {
                    is ApiResult.Error -> {

                    }
                    is ApiResult.Success -> {
                        _popularOfTheWeek.value += result.data
                        currentPage++
                    }
                }

            }
        }
    }
}