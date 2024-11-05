package com.example.cinevictor.presentation.features.popular.movieDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.network.MovieRepository
import com.example.cinevictor.domain.model.MovieDetailsCredit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailsCredit?>(null)
    val movieDetails: StateFlow<MovieDetailsCredit?> = _movieDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadMovieDetails(1)
    }

    private fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _isLoading.value = true

            val response = repository.getMovieCredits(movieId)

            _movieDetails.value = response

            _isLoading.value = false
        }
    }

}