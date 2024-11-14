package com.example.cinevictor.presentation.features.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.model.MovieDetailsCredit
import com.example.cinevictor.domain.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movieDetails = MutableStateFlow<List<Movie>?>(null)
    val movieDetails: StateFlow<List<Movie>?> = _movieDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            _isLoading.value = true

            val response = repository.getPopularMovies()

            when(response) {
                is ApiResult.Error -> {

                }

                is ApiResult.Success -> {
                    _movieDetails.value = response.data
                }
            }


            _isLoading.value = false
        }
    }

}