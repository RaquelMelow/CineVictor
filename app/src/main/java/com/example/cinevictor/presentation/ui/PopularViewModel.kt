package com.example.cinevictor.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.model.movie.MovieResponse
import com.example.cinevictor.data.network.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularListViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieResponse>?>(null)
    val movies: StateFlow<List<MovieResponse>?> = _movies

    fun fetchPopularMovies(apiKey: String, page: Int = 1) {
        viewModelScope.launch {
            val moviesList = repository.getPopularMovies(apiKey, page)

            _movies.value = moviesList
        }
    }
}

