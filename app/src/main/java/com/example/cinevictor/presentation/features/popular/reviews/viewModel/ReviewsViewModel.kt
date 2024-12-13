package com.example.cinevictor.presentation.features.popular.reviews.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.local.database.ReviewWithMovie
import com.example.cinevictor.data.repository.ReviewRepository
import com.example.cinevictor.domain.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReviewsViewModel(
    private val repository: ReviewRepository
) : ViewModel() {

    private var currentPage = 1
    private val _reviewsByMovie = MutableStateFlow<List<ReviewWithMovie>>(emptyList())
    val reviewsByMovie: StateFlow<List<ReviewWithMovie>> = _reviewsByMovie

    fun loadReviews() {
        viewModelScope.launch {
            repository.getReviewsByMovieId(currentPage).collect { result ->
                when (result) {
                    is ApiResult.Error -> {

                    }
                    is ApiResult.Success -> {
                        val currentReviews = _reviewsByMovie.value.toMutableList()
                        currentReviews += result.data
                        _reviewsByMovie.value = currentReviews.shuffled()
                        currentPage++
                    }
                }
            }
        }
    }
}
